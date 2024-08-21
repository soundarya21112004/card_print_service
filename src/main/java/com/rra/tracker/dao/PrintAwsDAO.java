package com.rra.tracker.dao;

import com.rra.tracker.model.slave.SampleWs;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class PrintAwsDAO {
    @Autowired
    private EntityManager awsEntityManager;
    Session session;
    public List<Object[]> getDataforOnDemandRequest(String pcn) {
        List<Object[]> list = new ArrayList<>();
        Query query = awsEntityManager.createNativeQuery("select id, json_data, province, city, zone, zip, agegroup, introducer, resident, registration_center_id, request_id, status, request_id1, rid " +
                "from print where request_id='"+pcn+"'");
        try {
            // Execute query
            list = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            awsEntityManager.clear();
            awsEntityManager.close();
        }
        return list;
    }


}
