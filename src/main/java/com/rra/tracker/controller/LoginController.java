package com.rra.tracker.controller;

import com.rra.tracker.dao.PrintSlaveDAO;
import com.rra.tracker.model.slave.CommonBean;
import com.rra.tracker.model.slave.LoginBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController

public class LoginController {

    @Autowired
    private PrintSlaveDAO slaveDAO;


    @GetMapping(value = "/loginByEmail")
    public ResponseEntity<LoginBean> loginByEmail(@PathParam("useremail") String useremail, @PathParam("password") String password) {

        System.out.println("Email " + useremail + " Password :" + password);
        LoginBean beanList = new LoginBean();
        try {

            BigInteger count = slaveDAO.loginCheck(useremail, password);
            System.out.println(count);
            if (count.intValue() > 0) {
                List<Object[]> obj=  slaveDAO.loginDetailsByEmail(useremail, password);
                for (Object[] o:
                        obj) {
                    beanList.setEmail((String) o[0]);
                    beanList.setName((String) o[1]);
                    beanList.setLocation((String) o[2]);
                    beanList.setUserid((String) o[3]);
                    beanList.setBelongsTo((String) o[4]);
                    beanList.setLastLoginTime((java.sql.Timestamp) o[5]);
                    beanList.setManufacturerId((String) o[6]);
                    beanList.setPassword((String) o[7]);
                }

            } else {
                beanList.setMessage("Either password mismatch or User not exists.");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>(beanList, HttpStatus.OK);
    }

    @GetMapping(value = "/syncByEmail")
    public ResponseEntity<ArrayList<LoginBean>> syncByEmail(@PathParam("useremail") String useremail) {


       ArrayList<LoginBean> albeanList = new ArrayList<>();
        try {

            String mfgId= slaveDAO.loadMfgId(useremail);
            System.out.println("Sync for "+mfgId);
            if (mfgId!=null) {
                List<Object[]> obj=  slaveDAO.subUnitDetailsByMfgId(mfgId);
                System.out.println(obj.size());
                for (Object[] o:
                        obj) {
                    LoginBean beanList = new LoginBean();
                    beanList.setEmail((String) o[0]);
                    beanList.setName((String) o[1]);
                    beanList.setLocation((String) o[2]);
                    beanList.setUserid((String) o[3]);
                    beanList.setBelongsTo((String) o[4]);
                    beanList.setLastLoginTime((java.sql.Timestamp) o[5]);
                    beanList.setManufacturerId((String) o[6]);
                    beanList.setLocationName((String) o[7]);
                    albeanList.add(beanList);
                }

            } else {
                LoginBean beanList = new LoginBean();
                beanList.setMessage("Email id not exists.");
                albeanList.add(beanList);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return new ResponseEntity<>(albeanList, HttpStatus.OK);
    }

}