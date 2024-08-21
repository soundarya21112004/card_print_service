package com.rra.tracker.dao;

import com.rra.tracker.model.master.PrintDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class PrintMasterDAO {
    Session session;
    @Autowired
    private EntityManager primeEntityManager;

    @SuppressWarnings("unchecked")
    public List<Object[]> getDataforBatchCreation(String location, BigDecimal totalcards) {
        List<Object[]> list = new ArrayList<>();
        Query query = primeEntityManager.createNativeQuery("select json_data,rid,request_id,id from print where status='0' and zone='" + location + "' and batchno is null").setMaxResults(totalcards.intValue());
        try {
            // Execute query
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            primeEntityManager.clear();
            primeEntityManager.close();
        }
        return list;
    }

    public List<Object[]> getDataforOnDemandRequest(String location, BigDecimal totalcards, String pcn) {
        List<Object[]> list = new ArrayList<>();
        Query query = primeEntityManager.createNativeQuery("select json_data,rid,request_id,id,batchno,status from print where request_id='" + pcn + "'").setMaxResults(totalcards.intValue());
        try {
            // Execute query
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            primeEntityManager.clear();
            primeEntityManager.close();
        }
        return list;
    }

    public boolean updateJobStatus(List<Integer> printid, String status, String batchno) {
        boolean flag = false;
        Transaction trans = null;
        System.out.println(printid);
        try {
            session = (Session) primeEntityManager.getDelegate();

            trans = session.getTransaction();

            Query query = primeEntityManager.createNativeQuery("update print set status='" + status + "',batchno='" + batchno + "' where id in :idlist");
            query.setParameter("idlist", printid);
            primeEntityManager.joinTransaction();
            int i = query.executeUpdate();
            System.out.println(i + "rows updated");
//            trans.commit();
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
            trans.rollback();


        } finally {
            session.close();

        }
        return flag;
    }

    public boolean updatePrinttatus(String pcn, String status, String batchno) {
        boolean flag = false;
        Transaction trans = null;
        try {
            session = (Session) primeEntityManager.getDelegate();

            trans = session.getTransaction();

            Query query = primeEntityManager.createNativeQuery("update print set status='" + status + "' where batchno ='" + batchno + "' and " +
                    "request_id='" + pcn + "'");
            primeEntityManager.joinTransaction();
            int i = query.executeUpdate();
            System.out.println(i + "rows updated");
//            trans.commit();
            flag = true;

        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
            trans.rollback();


        } finally {
//            session.close();

        }
        return flag;
    }

    public List<Object[]> listOfPrintedPCNByBatchno(String batchno) {
        List<Object[]> list = new ArrayList<>();
        Query query = primeEntityManager.createNativeQuery("select request_id,status from print where batchno='" + batchno + "'");
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

    public void writeInLocalPrintDb(PrintDTO obj) {
        boolean out = true;
//        PrintDTO sample = new PrintDTO();
        try {

            Query query = primeEntityManager.createNativeQuery("select count(*) from print");

            BigInteger maxid = (BigInteger) query.getSingleResult();
            if (maxid == BigInteger.ZERO) {
                maxid = BigInteger.ONE;
            } else {
                maxid = maxid.add(BigInteger.ONE);
            }

            System.out.println("maxid " + maxid);
            obj.setId(maxid.longValue());
            obj.setDownloadDate(new Timestamp(new Date().getTime()));
            obj.setPdfStatus(0L);


            primeEntityManager.joinTransaction();
            primeEntityManager.persist(obj);
            out = true;
        } catch (Exception e) {
            e.printStackTrace();
            out = false;
        }

    }
}
