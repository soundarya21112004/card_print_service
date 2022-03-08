package com.punjab.tracker.model;
import com.sun.istack.NotNull;
import com.sun.istack.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "TBL_CASE_CODE")
public class TblCaseCode {

    @Id
    @Column(name = "MARK_STATUS_ID")
    private long markStatusId;
    @Column(name = "ENTERED_BY")
    private String enteredBy;
    @Column(name = "ENTERED_DATE")
    private LocalDateTime enteredDate;
    @Column(name = "MANUFACTURER_INDENT_NO")
    private String manufacturerIndentNo;
    @Column(name = "STATUS_MODIFIED_BY")
    private String statusModifiedBy;
    @Column(name = "STATUS_MODIFIED_DATE")
    private LocalDateTime statusModifiedDate;
    @Column(name = "USDN_NO_ENCRYPT")
    private String usdnNoEncrypt;
    @Column(name = "MARKSTYPE_ID")
    private String markstypeId;
    @Column(name = "INVISIBLE_USDN_NO")
    private String invisibleUsdnNo;
    @Column(name = "PRODUCT_CODE")
    private String mstProduct;
    @Column(name = "BOX_ID")
    private String boxId;
    @Column(name = "MANUFACTURER_ID")
    private String manufacturerId;
//
//    @Column(name = "MFG_TYPE")
//    private int mfgType;
    @Column(name = "PRODUCT_CATEGORY")
    private BigDecimal productCategory;
    @Column(name = "BAR_CODE")
    private String barCode;
    @Column(name = "PRODUCT_PACK_TYPE")
    private BigDecimal productPackType;
    @Column(name = "ETN")
    private String etn;
    @Column(name = "PERMITNO")
    private String permitno;
    @Column(name = "TRANSPORTPASS")
    private String transportpass;
    @Column(name = "WHOLESALER_ID")
    private String wholesalerId;
    @Column(name = "WHOLESALER_PERMIT_NO")
    private String wholesalerPermitNo;
    @Column(name = "WHOLESALER_TRANSPORTPASS")
    private String wholesalerTransportpass;
    @Column(name = "PRODUCT_LABEL_ID")
    private String productLabelId;
    @Column(name = "BOTTLE_COUNT")
    private int bottleCount;

    public long getMarkStatusId() {
        return markStatusId;
    }

    public void setMarkStatusId(long markStatusId) {
        this.markStatusId = markStatusId;
    }

    public String getEnteredBy() {
        return enteredBy;
    }

    public void setEnteredBy(String enteredBy) {
        this.enteredBy = enteredBy;
    }

    public LocalDateTime getEnteredDate() {
        return enteredDate;
    }

    public void setEnteredDate(LocalDateTime enteredDate) {
        this.enteredDate = enteredDate;
    }

    public String getManufacturerIndentNo() {
        return manufacturerIndentNo;
    }

    public void setManufacturerIndentNo(String manufacturerIndentNo) {
        this.manufacturerIndentNo = manufacturerIndentNo;
    }

    public String getStatusModifiedBy() {
        return statusModifiedBy;
    }

    public void setStatusModifiedBy(String statusModifiedBy) {
        this.statusModifiedBy = statusModifiedBy;
    }

    public LocalDateTime getStatusModifiedDate() {
        return statusModifiedDate;
    }

    public void setStatusModifiedDate(LocalDateTime statusModifiedDate) {
        this.statusModifiedDate = statusModifiedDate;
    }

    public String getUsdnNoEncrypt() {
        return usdnNoEncrypt;
    }

    public void setUsdnNoEncrypt(String usdnNoEncrypt) {
        this.usdnNoEncrypt = usdnNoEncrypt;
    }

    public String getMarkstypeId() {
        return markstypeId;
    }

    public void setMarkstypeId(String markstypeId) {
        this.markstypeId = markstypeId;
    }

    public String getInvisibleUsdnNo() {
        return invisibleUsdnNo;
    }

    public void setInvisibleUsdnNo(String invisibleUsdnNo) {
        this.invisibleUsdnNo = invisibleUsdnNo;
    }

    public String getMstProduct() {
        return mstProduct;
    }

    public void setMstProduct(String mstProduct) {
        this.mstProduct = mstProduct;
    }

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

//    public int getMfgType() {
//        return mfgType;
//    }
//
//    public void setMfgType(int mfgType) {
//        this.mfgType = mfgType;
//    }

    public BigDecimal getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(BigDecimal productCategory) {
        this.productCategory = productCategory;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public BigDecimal getProductPackType() {
        return productPackType;
    }

    public void setProductPackType(BigDecimal productPackType) {
        this.productPackType = productPackType;
    }

    public String getEtn() {
        return etn;
    }

    public void setEtn(String etn) {
        this.etn = etn;
    }

    public String getPermitno() {
        return permitno;
    }

    public void setPermitno(String permitno) {
        this.permitno = permitno;
    }

    public String getTransportpass() {
        return transportpass;
    }

    public void setTransportpass(String transportpass) {
        this.transportpass = transportpass;
    }

    public String getWholesalerId() {
        return wholesalerId;
    }

    public void setWholesalerId(String wholesalerId) {
        this.wholesalerId = wholesalerId;
    }

    public String getWholesalerPermitNo() {
        return wholesalerPermitNo;
    }

    public void setWholesalerPermitNo(String wholesalerPermitNo) {
        this.wholesalerPermitNo = wholesalerPermitNo;
    }

    public String getWholesalerTransportpass() {
        return wholesalerTransportpass;
    }

    public void setWholesalerTransportpass(String wholesalerTransportpass) {
        this.wholesalerTransportpass = wholesalerTransportpass;
    }

    public String getProductLabelId() {
        return productLabelId;
    }

    public void setProductLabelId(String productLabelId) {
        this.productLabelId = productLabelId;
    }

    public int getBottleCount() {
        return bottleCount;
    }

    public void setBottleCount(int bottleCount) {
        this.bottleCount = bottleCount;
    }
}
