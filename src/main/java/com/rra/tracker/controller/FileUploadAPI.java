package com.rra.tracker.controller;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.rra.tracker.dao.PrintMasterDAO;
import com.rra.tracker.dao.PrintSlaveDAO;
import com.rra.tracker.model.slave.CommonBean;
import com.rra.tracker.model.slave.PrintBean;
import com.rra.tracker.model.slave.SampleWs;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.websocket.server.PathParam;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController

public class FileUploadAPI {

    @Autowired
    private PrintSlaveDAO slaveDAO;

    @Autowired
    private PrintMasterDAO masterDAO;

    @PostMapping(value = "/uploadPrintdataOld")
    public ResponseEntity<String> uploadFileOld(@RequestPart("responsefile") MultipartFile responsefile) {
        try {
            byte[] bytes = responsefile.getBytes();
            InputStream inputStream = new BufferedInputStream(responsefile.getInputStream());
            Path path = Paths.get(Objects.requireNonNull(responsefile.getOriginalFilename()));
            Files.write(path, bytes);

            StringBuilder resultStringBuilder = new StringBuilder();
            try (BufferedReader br
                         = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null) {
                    resultStringBuilder.append(line).append("\n");
                }
            }
            System.out.println(resultStringBuilder);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>("File Uploaded", HttpStatus.OK);
    }

    @PostMapping(value = "/uploadPrintdata")
    public ResponseEntity<String> uploadPrintdata(@RequestBody String content) throws JSONException {

        JSONArray obj = new JSONArray(content);

        for (int i = 0; i < obj.length(); i++) {
            JSONObject ob = (JSONObject) obj.get(i);
            JSONArray batchlist = (JSONArray) ob.get("batchList");
//            System.out.println("output "+batchlist);
            for (int j = 0; j < batchlist.length(); j++) {
                System.out.println(batchlist.get(j));
                String batchno = (String) ob.get("batchId");
                JSONObject batchdata = (JSONObject) batchlist.get(j);
                System.out.println(batchdata.get("pcn"));
                String pcn = (String) batchdata.get("pcn");
                String enc = new TestEnc().AESEncrypt(pcn);
                System.out.println(pcn + " Encrypted data :" + enc);
                masterDAO.updatePrinttatus(enc, "5", batchno);
                slaveDAO.updateRequestStatusInSlaveByBatchno(batchno, "PRINTING IN PROGRESS");
            }
        }
        return new ResponseEntity<>("Response Updated", HttpStatus.OK);
    }


    @PostMapping(value = "/checkPCNinMaster")
    public ResponseEntity<String> checkPCNinMaster(@RequestPart("pcn") String pcn) {

        System.out.println(pcn);
        return new ResponseEntity<>("File Uploaded", HttpStatus.OK);
    }

    @GetMapping(value = "/listOfBatchByLocation")
    public ResponseEntity<List<CommonBean>> listOfBatchByLocation(@PathParam("location") String locationname, @PathParam("requestMode") String requestMode) {

        System.out.println("location name " + locationname);
        List<CommonBean> beanList = new ArrayList<>();
        try {

            List<Object[]> batchlist = slaveDAO.listOfBatchByLocation(locationname, requestMode);

            for (Object[] batch :
                    batchlist) {
                CommonBean bean = new CommonBean();
                bean.setBatchno((String) batch[0]);
                bean.setTotalCards((int) batch[1]);
                bean.setName((String) batch[2]);
                bean.setJobid((int) batch[3]);
                bean.setEnteredDate((Date) batch[4]);
                bean.setLocation((String) batch[5]);
                bean.setRequestMode((String) batch[6]);
                bean.setLocationName((String) batch[7]);
                beanList.add(bean);

            }

        } catch (Exception e) {
            e.printStackTrace();
            CommonBean bean = new CommonBean();
            bean.setBatchno("No data");
            beanList.add(bean);
        }
        return new ResponseEntity<>(beanList, HttpStatus.OK);
    }


    @GetMapping(value = "/listOfDataByBatchNo")
    public ResponseEntity<List<SampleWs>> listOfDataByBatchNo(@PathParam("batchNo") String batchNo) {

        System.out.println("batchNo " + batchNo);
        List<SampleWs> beanList = new ArrayList<>();
        try {

            List<Object[]> batchlist = slaveDAO.listOfDataByBatchNo(batchNo);
            if (batchlist.size() > 0) {
                byte[] sign = slaveDAO.loadSignature(batchNo);
                for (Object[] batch :
                        batchlist) {
                    SampleWs bean = new SampleWs();
                    bean.setId((Integer) batch[0]);
                    bean.setZipFile((byte[]) batch[1]);
                    bean.setJobID((Integer) batch[2]);
                    bean.setFilename((String) batch[3]);
                    bean.setFileStatus((int) batch[4]);
                    if (sign != null) {
                        bean.setSignature(sign);
                    }
                    beanList.add(bean);

                }
            } else {
                SampleWs bean = new SampleWs();
                bean.setFilename("No data");
                beanList.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
            SampleWs bean = new SampleWs();
            bean.setFilename("No data");
            beanList.add(bean);
        }
        return new ResponseEntity<>(beanList, HttpStatus.OK);
    }

    @PostMapping(value = "/updateBatchStatus")
    public ResponseEntity<String> updateBatchStatus(@RequestParam("batchno") String batchno, @RequestParam("status") String status) {
        try {
            System.out.println(batchno);
            slaveDAO.updateRequestStatusInSlaveByBatchno(batchno, status);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while updating status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Status Updated", HttpStatus.OK);
    }

    @PostMapping(value = "/reDownloadBatch")
    public ResponseEntity<String> updateBatchStatusForReDownload(@RequestParam("batchno") String batchno, @RequestParam("remarks") String remarks) {
        try {
            System.out.println(batchno);
            int count = slaveDAO.updateReDownloadStatusInSlaveByBatchno(batchno, remarks, "REDOWNLOAD");
            if (count == 0) {
                return new ResponseEntity<>("Invalid Batch Number", HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                slaveDAO.updateReDownloadStatusInFile(batchno);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while updating status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Status Updated for Redownload", HttpStatus.OK);
    }

@CrossOrigin
    @GetMapping(value = "/listOfPrintedPcn")
    public ResponseEntity<List<PrintBean>> listOfPrintedPcn(@PathParam("batchno") String batchno) {
        System.out.println("batch request");
        List<PrintBean> beanList = new ArrayList<>();

        try {

            List<Object[]> batchlistDB = masterDAO.listOfPrintedPCNByBatchno(batchno);
            if (batchlistDB.size() > 0) {
                for (Object[] batch :
                        batchlistDB) {
                    PrintBean b = new PrintBean();
                    System.out.println(new TestEnc().AESDecrypt((String) batch[0]));
                    b.setPcn(new TestEnc().AESDecrypt((String) batch[0]));
                    b.setStatus((int) batch[1]);
                    b.setBatchno(batchno);
                    beanList.add(b);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            PrintBean b = new PrintBean();
            b.setPcn("");
            beanList.add(b);
        }
        return new ResponseEntity<>(beanList, HttpStatus.OK);
    }

}