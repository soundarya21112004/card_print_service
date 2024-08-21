package com.rra.tracker.dao;

import com.rra.tracker.model.slave.PrintSlaveDTO;
import com.rra.tracker.model.slave.SampleWs;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository

@Transactional
public class PrintSlaveDAO {
    Session session;
    @Autowired
    @Qualifier("productEntityManager")
    private EntityManager entityManager;

    private Transaction trans;

    @SuppressWarnings("unchecked")
    public List<PrintSlaveDTO> getUerInfo() {
        List<PrintSlaveDTO> list = new ArrayList<>();
        Query query = entityManager.createNativeQuery("select json_data from print");
        try {
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return list;
    }

    public BigInteger loadApprovedJobCount(String reqMode) {
        BigInteger list = BigInteger.ZERO;
        Query query = entityManager.createNativeQuery("select count(*) from tbl_job t1 " +
                "inner join mst_request_for_pasting t2 on t1.pasting_request_id=t2.request_id " +
                "where t2.status in('1','6') and t2.request_mode='" + reqMode + "'");
        try {
            list = (BigInteger) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return list;
    }

    public BigInteger loadRedownloadJobCount() {
        BigInteger list = BigInteger.ZERO;
        Query query = entityManager.createNativeQuery("select count(*) from tbl_job t1 " +
                "inner join mst_request_for_pasting t2 on t1.pasting_request_id=t2.request_id " +
                "where t2.status='7' and t1.status='APPROVED'");
        try {
            list = (BigInteger) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return list;
    }

    public List<Object[]> listOfApprovedJobs(String reqMode) {
        List<Object[]> list = new ArrayList<>();
        Query query = entityManager.createNativeQuery("select t1.pasting_request_id,t1.job_id,t2.balance_reels," +
                "t2.total_qty,t1.location_name,t1.batch_no,t2.pcn " +
                "from tbl_job t1 inner join mst_request_for_pasting t2 " +
                "on t1.pasting_request_id=t2.request_id where t2.status in('1','2','3','6') and t2.request_mode='" + reqMode + "'");
//                "on t1.pasting_request_id=t2.request_id where t2.status='2'");
        try {
            // Execute query
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return list;
    }

    public boolean updateJobStatusInSlave(int jobId, String status) {
        boolean flag = false;

        try {

            session = (Session) entityManager.getDelegate();
            trans = session.getTransaction();
            Query query = entityManager.createNativeQuery("update tbl_job set status='" + status + "' where job_id='" + jobId + "'");
            entityManager.joinTransaction();
            query.executeUpdate();
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
//            trans.rollback();


        } finally {
            session.close();


        }
        return flag;
    }


    public boolean updateRequestStatusInSlave(int jobId, String status) {
        boolean flag = false;

        try {

            session = (Session) entityManager.getDelegate();
            trans = session.getTransaction();
            Query query = entityManager.createNativeQuery("update mst_request_for_pasting set status='" + status + "' where request_id='" + jobId + "'");
            entityManager.joinTransaction();
            query.executeUpdate();
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
//            trans.rollback();


        } finally {
            session.close();


        }
        return flag;
    }

    public boolean updateZipFileInSlave(int jobId, String status) {
        boolean flag = false;

        try {

            session = (Session) entityManager.getDelegate();
            trans = session.getTransaction();
            Query query = entityManager.createNativeQuery("update sample_ws set filestatus='" + status + "' where job_id='" + jobId + "'");
            entityManager.joinTransaction();
            query.executeUpdate();
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
//            trans.rollback();


        } finally {
            session.close();


        }
        return flag;
    }

    public void writeCSVinDB(byte[] file, String batchno, int jobID, byte[] textfile) {
        boolean out = true;
        SampleWs sample = new SampleWs();
        try {

            Query query = entityManager.createNativeQuery("select count(*) from sample_ws");

            BigInteger maxid = (BigInteger) query.getSingleResult();
            if (maxid == BigInteger.ZERO) {
                maxid = BigInteger.ONE;
            } else {
                maxid = maxid.add(BigInteger.ONE);
            }

            System.out.println("maxid " + maxid);
            sample.setId(maxid.intValue());

            sample.setZipFile(file);
            sample.setPcnList(textfile);
            sample.setFilename(batchno);
            sample.setJobID(jobID);
            sample.setFileStatus(0);
            System.out.println(sample);
            entityManager.joinTransaction();
            entityManager.persist(sample);
            out = true;
        } catch (Exception e) {
            e.printStackTrace();
            out = false;
        }

    }

    public List<Object[]> listOfBatchByLocation(String locationCode, String reqMode) {
        List<Object[]> list = new ArrayList<>();
        Query query = entityManager.createNativeQuery("select t1.batch_no,t1.requested_codes,t2.manufacturername_en," +
                "t1.job_id,t1.entered_date,t1.location_code,t3.request_mode,t1.location_name  " +
                "from tbl_job t1 inner join mst_request_for_pasting t3 on t3.request_id=t1.pasting_request_id " +
                "inner join mst_manufacturer t2 on t1.manufacturer_id=t2.manufacturer_id " +
                "where t1.location_code='" + locationCode + "' " +
                "and t1.status in('BATCH CREATED','REDOWNLOADED')");
//                "and t1.status='BATCH CREATED'  and t3.request_mode='"+reqMode+"'");
//                "on t1.pasting_request_id=t2.request_id where t2.status='2'");
        try {
            // Execute query
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return list;
    }


    public BigInteger loginCheck(String useremail, String password) {
        BigInteger count = BigInteger.ZERO;
        Query query = entityManager.createNativeQuery("select count(*) from userdetails where email='" + useremail + "' " +
                "and verifycode_pwd='" + password + "'");
//                "on t1.pasting_request_id=t2.request_id where t2.status='2'");
        try {
            // Execute query
            count = (BigInteger) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return count;
    }

    public List<Object[]> loginDetailsByEmail(String useremail, String password) {
        List<Object[]> list = new ArrayList<>();
        Query query = entityManager.createNativeQuery("select t1.email,t1.firstname_en,t2.location," +
                "t1.userid,t1.belongs_to,t1.login_time,t2.manufacturer_id,t1.verifycode_pwd from userdetails t1 " +
                "inner join mst_manufacturer t2 on t1.manufacturer_id=t2.manufacturer_id " +
                "where t1.email='" + useremail + "' and t1.verifycode_pwd='" + password + "'");
//                "on t1.pasting_request_id=t2.request_id where t2.status='2'");
        try {
            // Execute query
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return list;
    }

    public List<Object[]> listOfDataByBatchNo(String batchno) {
        List<Object[]> list = new ArrayList<>();
        Query query = entityManager.createNativeQuery("select * from sample_ws where filename='" + batchno + "' and filestatus='0'");
//                "on t1.pasting_request_id=t2.request_id where t2.status='2'");
        try {
            // Execute query
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return list;
    }

    public byte[] loadSignature(String batchno) {
        byte[] list = new byte[0];
        Query query = entityManager.createNativeQuery("select t2.signature from tbl_job t1 " +
                "inner join mst_request_for_pasting t2 on t2.request_id=t1.pasting_request_id\n" +
                "where t1.batch_no='" + batchno + "'");
//                "on t1.pasting_request_id=t2.request_id where t2.status='2'");
        try {
            // Execute query
            list = (byte[]) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return list;
    }


    public boolean updateRequestStatusInSlaveByBatchno(String batchno, String status) {
        boolean flag = false;

        try {

            session = (Session) entityManager.getDelegate();
            trans = session.getTransaction();
            Query query = entityManager.createNativeQuery("update tbl_job set status='" + status + "' where batch_no='" + batchno + "'");
            entityManager.joinTransaction();
            query.executeUpdate();
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
//            trans.rollback();


        } finally {
            session.close();


        }
        return flag;
    }


    public int updateReDownloadStatusInSlaveByBatchno(String batchno, String remarks, String status) {
        int count = 0;
        System.out.println(remarks);
        try {

            session = (Session) entityManager.getDelegate();
            trans = session.getTransaction();
            Query query = entityManager.createNativeQuery("update tbl_job set status='" + status + "',remarks='" + remarks + "' where batch_no='" + batchno + "'");
            entityManager.joinTransaction();
            count = query.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            count = 0;
//            trans.rollback();


        } finally {
            session.close();


        }
        return count;
    }

    public int updateReDownloadStatusInFile(String batchno) {
        int count = 0;
        try {

            session = (Session) entityManager.getDelegate();
            trans = session.getTransaction();
            Query query = entityManager.createNativeQuery("update sample_ws set filestatus='1' where filename='" + batchno + "'");
            entityManager.joinTransaction();
            count = query.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            count = 0;
//            trans.rollback();


        } finally {
            session.close();


        }
        return count;
    }

    public List<Object[]> listOfRedownloadJobs() {
        List<Object[]> list = new ArrayList<>();
        Query query = entityManager.createNativeQuery("select t1.pasting_request_id,t1.job_id,t2.balance_reels," +
                "t2.total_qty,t1.location_name,t1.batch_no,t2.pcn " +
                "from tbl_job t1 inner join mst_request_for_pasting t2 " +
                "on t1.pasting_request_id=t2.request_id where t2.status in('7')");
//                "on t1.pasting_request_id=t2.request_id where t2.status='2'");
        try {
            // Execute query
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return list;
    }

    public String loadMfgId(String email) {
        String list = "";
        Query query = entityManager.createNativeQuery("select manufacturer_id from userdetails "+
                "where email='"+email+"'");
        try {
            list = (String) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return list;
    }
    public List<Object[]> subUnitDetailsByMfgId(String mfgId) {
        List<Object[]> list = new ArrayList<>();
        Query query = entityManager.createNativeQuery("select t2.email,t2.firstname_en,t3.location," +
                "t2.userid,t2.belongs_to,t2.login_time,t1.manufacturer_id,t3.province_importer " +
                "from tbl_factory t1 " +
                "inner join userdetails t2 on t1.manufacturer_id=t2.manufacturer_id " +
                "inner join mst_manufacturer t3 on t3.manufacturer_id=t2.manufacturer_id " +
                "where t1.base_mfg_id ='"+mfgId+"'");
//                "on t1.pasting_request_id=t2.request_id where t2.status='2'");
        try {
            // Execute query
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return list;
    }

}
