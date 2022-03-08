package com.punjab.tracker.model;
import com.sun.istack.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "TBL_MARK_USDN")
public class TblMarkUsdn {
    @Id
    private long sno;
    @Column(name = "USDN_NO")
    private String usdnNo;
    @Column(name = "MARK_SLNO")
    private BigDecimal markSlno;
    @Column(name = "MARK_STATUS_ID")
    private BigDecimal mstStatus;
    @Column(name = "ENTERED_BY")
    private String userdetailsByEnteredBy;
    @Column(name = "ENTERED_DATE")
    private LocalDateTime enteredDate;
    @Column(name = "MANUFACTURER_INDENT_NO")
    private String manufacturerIndentNo;
    @Column(name = "PROCESSLOCATION_ID")
    private String mstProcesslocation;
    @Column(name = "STATUS_MODIFIED_BY")
    private String userdetailsByStatusModifiedBy;
    @Column(name = "STATUS_MODIFIED_DATE")
    private LocalDateTime statusModifiedDate;
    @Column(name = "USDN_NO_ENCRYPT")
    private String usdnNoEncrypt;
    @Column(name = "STOCKINWARD_ID")
    private String tblStockInwarddetails;
    @Column(name = "USDN_NO_ENCRYPT_ACT")
    private String usdnNoEncryptAct;
    @Column(name = "MARKSTYPE_ID")
    private String mstMarkstype;
    @Column(name = "CUSTOMER_INDENT_NO")
    private String tblCustomerIndent;
    @Column(name = "STOCKGENERATION_ID")
    private String stockgenerationId;
    @Column(name = "INVISIBLE_USDN_NO")
    private String invisibleUsdnNo;
    @Column(name = "PRODUCT_CODE")
    private String mstProduct;
    @Column(name = "IP_ADDRESS")
    private String ipAddress;
    @Column(name = "MAC_ID")
    private String macId;
    @Column(name = "BOX_ID")
    private String boxId;
    @Column(name = "MANUFACTURER_ID")
    private String manufacturerId;
    @Column(name = "MFG_TYPE")
    private BigDecimal mfgType;

    @NotNull
    @Column(name = "PRODUCT_CATEGORY")
    private BigDecimal productCategory;


    @Column(name = "BAR_CODE")
    private String barCode;
    @Column(name = "PRODUCT_PACK_TYPE")
    private BigDecimal productPackType;
    @Column(name = "ETN")
    private String etn;
    @Column(name = "PERMITNO")
    private String permitNo;
    @Column(name = "TRANSPORTPASS")
    private String transportPass;
    @Column(name = "WHOLESALER_ID")
    private String wholesalerId;
    @Column(name = "WHOLESALER_PERMIT_NO")
    private String wholesalerPermitNo;
    @Column(name = "WHOLESALER_TRANSPORTPASS")
    private String wholesalerTransportpass;
    @Column(name = "PRODUCT_LABEL_ID")
    private String productLabelId;

    public TblMarkUsdn() {
    }


    public long getSno() {
        return sno;
    }

    public void setSno(long sno) {
        this.sno = sno;
    }

    public String getUsdnNo() {
        return usdnNo;
    }

    public void setUsdnNo(String usdnNo) {
        this.usdnNo = usdnNo;
    }

    public BigDecimal getMarkSlno() {
        return markSlno;
    }

    public void setMarkSlno(BigDecimal markSlno) {
        this.markSlno = markSlno;
    }

    public BigDecimal getMstStatus() {
        return mstStatus;
    }

    public void setMstStatus(BigDecimal mstStatus) {
        this.mstStatus = mstStatus;
    }

    public String getUserdetailsByEnteredBy() {
        return userdetailsByEnteredBy;
    }

    public void setUserdetailsByEnteredBy(String userdetailsByEnteredBy) {
        this.userdetailsByEnteredBy = userdetailsByEnteredBy;
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

    public String getMstProcesslocation() {
        return mstProcesslocation;
    }

    public void setMstProcesslocation(String mstProcesslocation) {
        this.mstProcesslocation = mstProcesslocation;
    }

    public String getUserdetailsByStatusModifiedBy() {
        return userdetailsByStatusModifiedBy;
    }

    public void setUserdetailsByStatusModifiedBy(String userdetailsByStatusModifiedBy) {
        this.userdetailsByStatusModifiedBy = userdetailsByStatusModifiedBy;
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

    public String getTblStockInwarddetails() {
        return tblStockInwarddetails;
    }

    public void setTblStockInwarddetails(String tblStockInwarddetails) {
        this.tblStockInwarddetails = tblStockInwarddetails;
    }

    public String getUsdnNoEncryptAct() {
        return usdnNoEncryptAct;
    }

    public void setUsdnNoEncryptAct(String usdnNoEncryptAct) {
        this.usdnNoEncryptAct = usdnNoEncryptAct;
    }

    public String getMstMarkstype() {
        return mstMarkstype;
    }

    public void setMstMarkstype(String mstMarkstype) {
        this.mstMarkstype = mstMarkstype;
    }

    public String getTblCustomerIndent() {
        return tblCustomerIndent;
    }

    public void setTblCustomerIndent(String tblCustomerIndent) {
        this.tblCustomerIndent = tblCustomerIndent;
    }

    public String getStockgenerationId() {
        return stockgenerationId;
    }

    public void setStockgenerationId(String stockgenerationId) {
        this.stockgenerationId = stockgenerationId;
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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId;
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

    public BigDecimal getMfgType() {
        return mfgType;
    }

    public void setMfgType(BigDecimal mfgType) {
        this.mfgType = mfgType;
    }

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

    public String getPermitNo() {
        return permitNo;
    }

    public void setPermitNo(String permitNo) {
        this.permitNo = permitNo;
    }

    public String getTransportPass() {
        return transportPass;
    }

    public void setTransportPass(String transportPass) {
        this.transportPass = transportPass;
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
}
