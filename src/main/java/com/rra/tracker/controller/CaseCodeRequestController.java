package com.rra.tracker.controller;

import com.rra.tracker.dao.PrintMasterDAO;
import com.rra.tracker.dao.PrintSlaveDAO;
//import net.lingala.zip4j.core.ZipFile;
//import net.lingala.zip4j.model.ZipParameters;
//import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/api")
public class CaseCodeRequestController {
    private static SecretKey secretKey;
    private static final String mykey = "67556B58703273357638792F423F4528482B4D6250655368566D597133743677";
    @Autowired
    private PrintMasterDAO masterDAO;
    @Autowired
    private PrintSlaveDAO slaveDAO;
    private SendEmail em;

    public static void setKey() {
        try {
            byte[] raw = HexToByteNew(mykey);

            secretKey = new SecretKeySpec(raw, "AES");
        } catch (Exception e) {
            System.out.println("setKey: " + e.toString());
        }

    }

    public static byte[] HexToByteNew(String keyData) {

        byte[] val = null;
        try {
            val = new byte[keyData.length() / 2];
            for (int i = 0; i < val.length; i++) {
                int index = i * 2;
                int j = Integer.parseInt(keyData.substring(index, index + 2), 16);
                val[i] = (byte) j;
            }

            return val;

        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
            return val;
        }

    }

    public static String ByteToHexNew(byte[] publicKey) {
        String outValue = "";
        StringBuffer retString = new StringBuffer();
        try {


            for (int i = 0; i < publicKey.length; ++i) {
                retString.append(Integer.toHexString(0x0100 + (publicKey[i] & 0x00FF)).substring(1));
            }
            outValue = retString.toString();
            return outValue;


        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
            return outValue;
        }


    }

    @Scheduled(fixedDelay = 60000, initialDelay = 100)//in milliseconds
    public void listOfApprovedBatch() throws IOException {
        Logging.writeLogJson(new Date(), "Monitoring Job running..");
        List<Integer> idList;
        BigInteger batchapprovedCount = slaveDAO.loadApprovedJobCount("BATCH");
        BigInteger demandapprovedCount = slaveDAO.loadApprovedJobCount("ON_DEMAND");
        BigInteger reDownloadCount = slaveDAO.loadRedownloadJobCount();
        System.out.println("Total Batch Approved count" + batchapprovedCount + " :: Total OnDemand Approved Count " + demandapprovedCount + " :: Total Redownload Approved Count " + reDownloadCount);
        try {
            if (batchapprovedCount.intValue() > 0) {
                List<Object[]> joblist = slaveDAO.listOfApprovedJobs("BATCH");
                for (Object[] job :
                        joblist) {
                    System.out.println("REQUEST ID " + job[0] + " :: JOB ID " + job[1] + " :: CARDS PER BATCH " + job[2] +
                            " :: TOTAL CARDS " + job[3] + " :: LOCATION NAME " + job[4] + " :: BATCH NUMBER " + job[5]);
                    int jobid = (int) job[1];
                    BigDecimal totalcards = (BigDecimal) job[3];
                    String location = (String) job[4];
                    String batchno = (String) job[5];

                    slaveDAO.updateJobStatusInSlave(jobid, "JOB PICKED FOR BATCH CREATION");
                    List<Object[]> listOfMasterData = masterDAO.getDataforBatchCreation(location, totalcards);

                    System.out.println("Job Id " + jobid + " Requested Count " + totalcards + " Count from Master :" + listOfMasterData.size());
                    idList = new ArrayList<>();
                    if (listOfMasterData.size() == totalcards.intValue()) {
                        for (Object[] printid :
                                listOfMasterData) {
                            idList.add((Integer) printid[3]);
                        }
                        System.out.println("Valid count");
                        createZipInDb(listOfMasterData, batchno, jobid);
                        masterDAO.updateJobStatus(idList, "1", batchno);
                        slaveDAO.updateJobStatusInSlave(jobid, "BATCH CREATED");
                        slaveDAO.updateRequestStatusInSlave(jobid, "4");
                        em = new SendEmail();
                        em.configfile();
                        em.sendExternalMail("mnprb2011@gmail.com", "Status of Card Print", "BATCH CREATED");

                    } else {

                        slaveDAO.updateJobStatusInSlave(jobid, "REQUESTED COUNT NOT AVAILABLE IN MASTER");
                        slaveDAO.updateRequestStatusInSlave(jobid, "3");
                        System.out.println("Invalid count");
                    }

                }

            }
            if (demandapprovedCount.intValue() > 0) {
                System.out.println("On Demand Request Processing");
                List<Object[]> joblist = slaveDAO.listOfApprovedJobs("ON_DEMAND");
                for (Object[] job :
                        joblist) {
                    System.out.println("REQUEST ID " + job[0] + " :: JOB ID " + job[1] + " :: CARDS PER BATCH " + job[2] +
                            " :: TOTAL CARDS " + job[3] + " :: LOCATION NAME " + job[4] + " :: BATCH NUMBER " + job[5] + " :: PCN NUMBER " + job[6]);
                    int jobid = (int) job[1];
                    BigDecimal totalcards = (BigDecimal) job[3];
                    String location = (String) job[4];
                    String batchno = (String) job[5];
                    String pcn = (String) job[6];

                    slaveDAO.updateJobStatusInSlave(jobid, "JOB PICKED FOR BATCH CREATION");
                    String encryptedPCN = AESEncrypt(pcn);
                    System.out.println("encryptedPCN " + encryptedPCN);
                    List<Object[]> listOfMasterData = masterDAO.getDataforOnDemandRequest(location, totalcards, encryptedPCN);

                    System.out.println("Job Id " + jobid + " Requested Count " + totalcards + " Count from Master :" + listOfMasterData.size());
                    if (listOfMasterData.size() != 0) {
                        for (Object[] o :
                                listOfMasterData) {
                            idList = new ArrayList<>();
                            for (Object[] printid :
                                    listOfMasterData) {
                                idList.add((Integer) printid[3]);
                            }
                            System.out.println(o[4]);
                            System.out.println("Valid count");
                            if (o[4] == null) {
                                createZipInDb(listOfMasterData, batchno, jobid);
                                masterDAO.updateJobStatus(idList, "1", batchno);
                                slaveDAO.updateJobStatusInSlave(jobid, "BATCH CREATED");
                                slaveDAO.updateRequestStatusInSlave(jobid, "4");
                            } else {
                                slaveDAO.updateJobStatusInSlave(jobid, "BATCH ALREADY CREATED -" + o[4]);
                                slaveDAO.updateRequestStatusInSlave(jobid, "5");
                            }

                        }


                    } else {
                        slaveDAO.updateJobStatusInSlave(jobid, "PCN NOT AVAILABLE FOR PRINT");
                        slaveDAO.updateRequestStatusInSlave(jobid, "6");
                    }


                }

            }
            if (reDownloadCount.intValue() > 0) {
                List<Object[]> joblist = slaveDAO.listOfRedownloadJobs();
                for (Object[] job :
                        joblist) {
                    int jobid = (int) job[1];
                    slaveDAO.updateJobStatusInSlave(jobid, "REDOWNLOADED");
                    slaveDAO.updateRequestStatusInSlave(jobid, "4");
                    slaveDAO.updateZipFileInSlave(jobid, "0");//Redownload status in sample ws
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createZipInDb(List<Object[]> masterlist, String batchno, int jobid) throws IOException {
        Logging.writeLogJson(new Date(), "Monitoring Job running..");

        System.out.println("Slave count" + slaveDAO.getUerInfo().size());
        File catalinaBase = new File(System.getProperty("catalina.base")).getAbsoluteFile();
//        String imagePath = new File(catalinaBase, "bin/SignImage/").toString(); //test
        String imagePath = "D://"; //test

        String textfile = imagePath + "/" + batchno + ".txt";
        String directoryname = imagePath + "/" + batchno;
        FileWriter writer = new FileWriter(textfile);
        for (Object[] printdata :
                masterlist) {
            System.out.println(AESDecrypt((String) printdata[2]));
            String decData = AESDecrypt((String) printdata[2]);
            if (decData == null) {
                decData = (String) printdata[1];
            }
            File directory = new File(directoryname);
            if (!directory.exists()) {
                directory.mkdir();

            }
            FileWriter file = new FileWriter(directory + "/" + decData + ".txt");
            file.write(String.valueOf(printdata[0]));
            file.close();
            writer.write(decData + System.lineSeparator());


        }
        writer.close();
        String destZipFile = imagePath + "/" + batchno + ".zip";
        zip(imagePath + "/" + batchno, destZipFile);

        byte[] buffer = Files.readAllBytes(Paths.get(destZipFile));
        byte[] textfilebuffer = Files.readAllBytes(Paths.get(textfile));
        slaveDAO.writeCSVinDB(buffer, batchno, jobid, textfilebuffer);
        Files.delete(Paths.get(destZipFile));
        Files.delete(Paths.get(textfile));
        FileUtils.deleteDirectory(new File(directoryname));

    }

    public void zip(String sourcDirPath, String zipPath) throws IOException {
        Path zipFile = Files.createFile(Paths.get(zipPath));
        System.out.println("sourcDirPath " + sourcDirPath);
        System.out.println("zipPath " + zipPath);
        Path sourceDirPath = Paths.get(sourcDirPath);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile));
             Stream<Path> paths = Files.walk(sourceDirPath)) {
            paths
                    .filter(path -> !Files.isDirectory(path))
                    .forEach(path -> {
                        ZipEntry zipEntry = new ZipEntry(sourceDirPath.relativize(path).toString());
                        try {
                            zipOutputStream.putNextEntry(zipEntry);
                            Files.copy(path, zipOutputStream);
                            zipOutputStream.closeEntry();
                        } catch (IOException e) {
                            System.err.println(e);
                        }
                    });
        }

        System.out.println("Zip is created at : " + zipFile);
    }

    public String AESDecrypt(String strToDecrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            // return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt))); //For Base64 input
            System.out.println("Decrypted Is Working");
            return new String(cipher.doFinal(HexToByteNew(strToDecrypt))); //For Hex input
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }

    public String AESEncrypt(String strToEncrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            //return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8"))); //For Base64 output
            return ByteToHexNew(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));  //For Hex output
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
}