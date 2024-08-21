package com.rra.tracker.model.slave;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.security.Timestamp;
import java.sql.Date;

@Entity
@Table(name = "PRINT")
public class PrintSlaveDTO {
    private Long id;
    private String jsonData;
    private String province;
    private String city;
    private String zone;
    private String zip;
    private Long agegroup;
    private String introducer;
    private String resident;
    private String registrationCenterId;
    private Timestamp registrationDate;
    private Timestamp downloadDate;
    private String requestId;
    private Long status;
    private String requestId1;
    private Date birthdate;
    private String rid;
    private String pdfData;
    private String qrJson;
    private Long pdfStatus;
    private String qrPhoto;

    @Id
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJsonData() {
        return this.jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZone() {
        return this.zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Long getAgegroup() {
        return this.agegroup;
    }

    public void setAgegroup(Long agegroup) {
        this.agegroup = agegroup;
    }

    public String getIntroducer() {
        return this.introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer;
    }

    public String getResident() {
        return this.resident;
    }

    public void setResident(String resident) {
        this.resident = resident;
    }

    public String getRegistrationCenterId() {
        return this.registrationCenterId;
    }

    public void setRegistrationCenterId(String registrationCenterId) {
        this.registrationCenterId = registrationCenterId;
    }

    public Timestamp getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Timestamp getDownloadDate() {
        return this.downloadDate;
    }

    public void setDownloadDate(Timestamp downloadDate) {
        this.downloadDate = downloadDate;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getStatus() {
        return this.status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getRequestId1() {
        return this.requestId1;
    }

    public void setRequestId1(String requestId1) {
        this.requestId1 = requestId1;
    }

    public Date getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getRid() {
        return this.rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getPdfData() {
        return this.pdfData;
    }

    public void setPdfData(String pdfData) {
        this.pdfData = pdfData;
    }

    public String getQrJson() {
        return this.qrJson;
    }

    public void setQrJson(String qrJson) {
        this.qrJson = qrJson;
    }

    public Long getPdfStatus() {
        return this.pdfStatus;
    }

    public void setPdfStatus(Long pdfStatus) {
        this.pdfStatus = pdfStatus;
    }

    public String getQrPhoto() {
        return this.qrPhoto;
    }

    public void setQrPhoto(String qrPhoto) {
        this.qrPhoto = qrPhoto;
    }


}
