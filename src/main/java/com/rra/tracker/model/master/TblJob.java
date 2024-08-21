package com.rra.tracker.model.master;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.security.Timestamp;
import java.sql.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBL_JOB")

public class TblJob {
    private int sno;
    private String batchNo;
    private Integer requestedCodes;
    private Integer balanceCodes;
    private String productCategory;
    private String mstManufacturer;
    private String enteredBy;
    private String approvedBy;
    private String rejectedBy;
    private String status;
    private String remarks;
    private String locationCode;
    private String locationName;
    private java.util.Date enteredDate;
    private java.util.Date approvedDate;
    private java.util.Date rejectedDate;
    private int jobId;

@Id
    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Integer getRequestedCodes() {
        return requestedCodes;
    }

    public void setRequestedCodes(Integer requestedCodes) {
        this.requestedCodes = requestedCodes;
    }

    public Integer getBalanceCodes() {
        return balanceCodes;
    }

    public void setBalanceCodes(Integer balanceCodes) {
        this.balanceCodes = balanceCodes;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getMstManufacturer() {
        return mstManufacturer;
    }

    public void setMstManufacturer(String mstManufacturer) {
        this.mstManufacturer = mstManufacturer;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getRejectedBy() {
        return rejectedBy;
    }

    public void setRejectedBy(String rejectedBy) {
        this.rejectedBy = rejectedBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public java.util.Date getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(java.util.Date enteredDate) {
        this.enteredDate = enteredDate;
    }

    public java.util.Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(java.util.Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public java.util.Date getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(java.util.Date rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
}
