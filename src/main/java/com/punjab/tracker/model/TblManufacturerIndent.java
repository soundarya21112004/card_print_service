package com.punjab.tracker.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "TBL_MANUFACTURER_INDENT")
public class TblManufacturerIndent {
    @Id
    @Column(name = "SNO")
    private long sno;
    @Column(name = "MANUFACTURER_INDENT_NO")
    private String manufacturerIndentNo;

    @Column(name = "MANUFACTURER_INDENT_DATE")
    private LocalDateTime manufacturerIndentDate;
    @Column(name = "MANUFACTURER_ID")
    private String mstManufacturer;
    @Column(name = "PRODUCT_CODE")
    private String mstProduct;
    @Column(name = "QUANTITY")
    private BigDecimal quantity;
    @Column(name = "STATUS_ID")
    private BigDecimal mstStatus;
    @Column(name = "ENTERED_BY")
    private String userdetailsByEnteredBy;
    @Column(name = "ENTERED_DATE")
    private LocalDateTime enteredDate;
    @Column(name = "APPLICATION_NUMBER")
    private String applicationNumber;
    @Column(name = "ENTRY_DATE_STRING")
    private String entryDateString;
    @Column(name = "START_SERIAL_NO")
    private String startSerialNo;
    @Column(name = "END_SERIAL_NO")
    private String endSerialNo;
    @Column(name = "ENCODE_DATE")
    private LocalDateTime encodeDate;
    @Column(name = "PAYMENT_MODE")
    private String paymentMode;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "REFERENCE_NUMBER")
    private String referenceNumber;
    @Column(name = "PAYMENT_STATUS")
    private String paymentStatus;
    @Column(name = "BALANCE_QTY")
    private BigDecimal balanceQty;
    @Column(name = "PACKING_STATUS")
    private BigDecimal packingStatus;
    @Column(name = "DESPATCH_STATUS")
    private BigDecimal despatchStatus;
    @Column(name = "ENCODED_QTY")
    private BigDecimal encodedQty;
    @Column(name = "PACKED_QTY")
    private BigDecimal packedQty;
//    @Column(name = "SCANNED_DOCUMENT")
//    private String scannedDocument;
    @Column(name = "RE_EXPORT")
    private Character reExport;
    @Column(name = "INDENT_ORIGIN")
    private String indentOrigin;
    @Column(name = "PRODUCT_BRAND")
    private String productBrand;
    @Column(name = "PRODUCTNAME")
    private String productname;
    @Column(name = "IP_ADDRESS")
    private String ipAddress;
    @Column(name = "MAC_ID")
    private String macId;
    @Column(name = "CUSDEC_NUMBER")
    private String cusdecNumber;
    @Column(name = "CUSDEC_PRODUCT")
    private String cusdecProduct;
    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "SPN_NUMBER")
    private String spnNumber;
    @Column(name = "VOLUME_CLASSIFICATION")
    private String mstTaxClassification;
//    @Column(name = "PAYMENT_RECEIPT")
//    private String paymentReceipt;
    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    @Column(name = "PAYMENT_BY")
    private String userdetailsByPaymentBy;
    @Column(name = "PAYMENT_DATE")
    private LocalDateTime paymentDate;
    @Column(name = "ACTUAL_QUANTITY")
    private BigDecimal actualQuantity;
//    @Column(name = "IMPORT_DECLARATION_FORM")
//    private String importDeclarationForm;
    @Column(name = "MODIFIED_BY")
    private String userdetailsByModifiedBy;
    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;
    @Column(name = "APPROVED_BY")
    private String userdetailsByApprovedBy;
    @Column(name = "APPROVED_DATE")
    private LocalDateTime approvedDate;
    @Column(name = "FIRST_APPROVED_BY")
    private String userdetailsByFirstApprovedBy;
    @Column(name = "SECOND_APPROVED_BY")
    private String userdetailsBySecondApprovedBy;
    @Column(name = "THIRD_APPROVED_BY")
    private String userdetailsByThirdApprovedBy;
    @Column(name = "REJECTED_BY")
    private String userdetailsByRejectedBy;
    @Column(name = "FIRST_REJECTED_BY")
    private String userdetailsByFirstRejectedBy;
    @Column(name = "SECOND_REJECTED_BY")
    private String userdetailsBySecondRejectedBy;
    @Column(name = "THIRD_REJECTED_BY")
    private String userdetailsByThirdRejectedBy;
    @Column(name = "FIRST_APPROVED_DATE")
    private LocalDateTime firstApprovedDate;
    @Column(name = "SECOND_APPROVED_DATE")
    private LocalDateTime secondApprovedDate;
    @Column(name = "THIRD_APPROVED_DATE")
    private LocalDateTime thirdApprovedDate;
    @Column(name = "REJECTED_DATE")
    private LocalDateTime rejectedDate;
    @Column(name = "FIRST_REJECTED_DATE")
    private LocalDateTime firstRejectedDate;
    @Column(name = "SECOND_REJECTED_DATE")
    private LocalDateTime secondRejectedDate;
    @Column(name = "THIRD_REJECTED_DATE")
    private LocalDateTime thirdRejectedDate;
    @Column(name = "REJECTION_REMARK")
    private String rejectionRemark;
    @Column(name = "COLOR_ID")
    private BigDecimal mstColor;
    @Column(name = "ISEXPORT")
    private Character isexport;
    @Column(name = "is_digital_code")
    private String isDigitalCode;
    @Column(name = "FACTORY_ID")
    private String tblFactory;
    @Column(name = "CATEGORY_ID")
    private BigDecimal mstProductcategory;


    @Column(name = "TOTAL_QUANTITY")
    private BigDecimal totalQuantity;
    @Column(name = "MFG_DATE")
    private LocalDateTime mfgDate;
    @Column(name = "EXPIRY_DATE")
    private LocalDateTime expiryDate;
    @Column(name = "PACK_TYPE")
    private BigDecimal mstProductPackType;



    public long getSno() {
        return sno;
    }

    public void setSno(long sno) {
        this.sno = sno;
    }

    public String getManufacturerIndentNo() {
        return manufacturerIndentNo;
    }

    public void setManufacturerIndentNo(String manufacturerIndentNo) {
        this.manufacturerIndentNo = manufacturerIndentNo;
    }

    public LocalDateTime getManufacturerIndentDate() {
        return manufacturerIndentDate;
    }

    public void setManufacturerIndentDate(LocalDateTime manufacturerIndentDate) {
        this.manufacturerIndentDate = manufacturerIndentDate;
    }

    public String getMstManufacturer() {
        return mstManufacturer;
    }

    public void setMstManufacturer(String mstManufacturer) {
        this.mstManufacturer = mstManufacturer;
    }

    public String getMstProduct() {
        return mstProduct;
    }

    public void setMstProduct(String mstProduct) {
        this.mstProduct = mstProduct;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
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

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public String getEntryDateString() {
        return entryDateString;
    }

    public void setEntryDateString(String entryDateString) {
        this.entryDateString = entryDateString;
    }

    public String getStartSerialNo() {
        return startSerialNo;
    }

    public void setStartSerialNo(String startSerialNo) {
        this.startSerialNo = startSerialNo;
    }

    public String getEndSerialNo() {
        return endSerialNo;
    }

    public void setEndSerialNo(String endSerialNo) {
        this.endSerialNo = endSerialNo;
    }

    public LocalDateTime getEncodeDate() {
        return encodeDate;
    }

    public void setEncodeDate(LocalDateTime encodeDate) {
        this.encodeDate = encodeDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getBalanceQty() {
        return balanceQty;
    }

    public void setBalanceQty(BigDecimal balanceQty) {
        this.balanceQty = balanceQty;
    }

    public BigDecimal getPackingStatus() {
        return packingStatus;
    }

    public void setPackingStatus(BigDecimal packingStatus) {
        this.packingStatus = packingStatus;
    }

    public BigDecimal getDespatchStatus() {
        return despatchStatus;
    }

    public void setDespatchStatus(BigDecimal despatchStatus) {
        this.despatchStatus = despatchStatus;
    }

    public BigDecimal getEncodedQty() {
        return encodedQty;
    }

    public void setEncodedQty(BigDecimal encodedQty) {
        this.encodedQty = encodedQty;
    }

    public BigDecimal getPackedQty() {
        return packedQty;
    }

    public void setPackedQty(BigDecimal packedQty) {
        this.packedQty = packedQty;
    }

    public Character getReExport() {
        return reExport;
    }

    public void setReExport(Character reExport) {
        this.reExport = reExport;
    }

    public String getIndentOrigin() {
        return indentOrigin;
    }

    public void setIndentOrigin(String indentOrigin) {
        this.indentOrigin = indentOrigin;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
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

    public String getCusdecNumber() {
        return cusdecNumber;
    }

    public void setCusdecNumber(String cusdecNumber) {
        this.cusdecNumber = cusdecNumber;
    }

    public String getCusdecProduct() {
        return cusdecProduct;
    }

    public void setCusdecProduct(String cusdecProduct) {
        this.cusdecProduct = cusdecProduct;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSpnNumber() {
        return spnNumber;
    }

    public void setSpnNumber(String spnNumber) {
        this.spnNumber = spnNumber;
    }

    public String getMstTaxClassification() {
        return mstTaxClassification;
    }

    public void setMstTaxClassification(String mstTaxClassification) {
        this.mstTaxClassification = mstTaxClassification;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUserdetailsByPaymentBy() {
        return userdetailsByPaymentBy;
    }

    public void setUserdetailsByPaymentBy(String userdetailsByPaymentBy) {
        this.userdetailsByPaymentBy = userdetailsByPaymentBy;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(BigDecimal actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public String getUserdetailsByModifiedBy() {
        return userdetailsByModifiedBy;
    }

    public void setUserdetailsByModifiedBy(String userdetailsByModifiedBy) {
        this.userdetailsByModifiedBy = userdetailsByModifiedBy;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getUserdetailsByApprovedBy() {
        return userdetailsByApprovedBy;
    }

    public void setUserdetailsByApprovedBy(String userdetailsByApprovedBy) {
        this.userdetailsByApprovedBy = userdetailsByApprovedBy;
    }

    public LocalDateTime getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(LocalDateTime approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getUserdetailsByFirstApprovedBy() {
        return userdetailsByFirstApprovedBy;
    }

    public void setUserdetailsByFirstApprovedBy(String userdetailsByFirstApprovedBy) {
        this.userdetailsByFirstApprovedBy = userdetailsByFirstApprovedBy;
    }

    public String getUserdetailsBySecondApprovedBy() {
        return userdetailsBySecondApprovedBy;
    }

    public void setUserdetailsBySecondApprovedBy(String userdetailsBySecondApprovedBy) {
        this.userdetailsBySecondApprovedBy = userdetailsBySecondApprovedBy;
    }

    public String getUserdetailsByThirdApprovedBy() {
        return userdetailsByThirdApprovedBy;
    }

    public void setUserdetailsByThirdApprovedBy(String userdetailsByThirdApprovedBy) {
        this.userdetailsByThirdApprovedBy = userdetailsByThirdApprovedBy;
    }

    public String getUserdetailsByRejectedBy() {
        return userdetailsByRejectedBy;
    }

    public void setUserdetailsByRejectedBy(String userdetailsByRejectedBy) {
        this.userdetailsByRejectedBy = userdetailsByRejectedBy;
    }

    public String getUserdetailsByFirstRejectedBy() {
        return userdetailsByFirstRejectedBy;
    }

    public void setUserdetailsByFirstRejectedBy(String userdetailsByFirstRejectedBy) {
        this.userdetailsByFirstRejectedBy = userdetailsByFirstRejectedBy;
    }

    public String getUserdetailsBySecondRejectedBy() {
        return userdetailsBySecondRejectedBy;
    }

    public void setUserdetailsBySecondRejectedBy(String userdetailsBySecondRejectedBy) {
        this.userdetailsBySecondRejectedBy = userdetailsBySecondRejectedBy;
    }

    public String getUserdetailsByThirdRejectedBy() {
        return userdetailsByThirdRejectedBy;
    }

    public void setUserdetailsByThirdRejectedBy(String userdetailsByThirdRejectedBy) {
        this.userdetailsByThirdRejectedBy = userdetailsByThirdRejectedBy;
    }

    public LocalDateTime getFirstApprovedDate() {
        return firstApprovedDate;
    }

    public void setFirstApprovedDate(LocalDateTime firstApprovedDate) {
        this.firstApprovedDate = firstApprovedDate;
    }

    public LocalDateTime getSecondApprovedDate() {
        return secondApprovedDate;
    }

    public void setSecondApprovedDate(LocalDateTime secondApprovedDate) {
        this.secondApprovedDate = secondApprovedDate;
    }

    public LocalDateTime getThirdApprovedDate() {
        return thirdApprovedDate;
    }

    public void setThirdApprovedDate(LocalDateTime thirdApprovedDate) {
        this.thirdApprovedDate = thirdApprovedDate;
    }

    public LocalDateTime getRejectedDate() {
        return rejectedDate;
    }

    public void setRejectedDate(LocalDateTime rejectedDate) {
        this.rejectedDate = rejectedDate;
    }

    public LocalDateTime getFirstRejectedDate() {
        return firstRejectedDate;
    }

    public void setFirstRejectedDate(LocalDateTime firstRejectedDate) {
        this.firstRejectedDate = firstRejectedDate;
    }

    public LocalDateTime getSecondRejectedDate() {
        return secondRejectedDate;
    }

    public void setSecondRejectedDate(LocalDateTime secondRejectedDate) {
        this.secondRejectedDate = secondRejectedDate;
    }

    public LocalDateTime getThirdRejectedDate() {
        return thirdRejectedDate;
    }

    public void setThirdRejectedDate(LocalDateTime thirdRejectedDate) {
        this.thirdRejectedDate = thirdRejectedDate;
    }

    public String getRejectionRemark() {
        return rejectionRemark;
    }

    public void setRejectionRemark(String rejectionRemark) {
        this.rejectionRemark = rejectionRemark;
    }

    public BigDecimal getMstColor() {
        return mstColor;
    }

    public void setMstColor(BigDecimal mstColor) {
        this.mstColor = mstColor;
    }

    public Character getIsexport() {
        return isexport;
    }

    public void setIsexport(Character isexport) {
        this.isexport = isexport;
    }

    public String getIsDigitalCode() {
        return isDigitalCode;
    }

    public void setIsDigitalCode(String isDigitalCode) {
        this.isDigitalCode = isDigitalCode;
    }

    public String getTblFactory() {
        return tblFactory;
    }

    public void setTblFactory(String tblFactory) {
        this.tblFactory = tblFactory;
    }

    public BigDecimal getMstProductcategory() {
        return mstProductcategory;
    }

    public void setMstProductcategory(BigDecimal mstProductcategory) {
        this.mstProductcategory = mstProductcategory;
    }

    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(BigDecimal totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public LocalDateTime getMfgDate() {
        return mfgDate;
    }

    public void setMfgDate(LocalDateTime mfgDate) {
        this.mfgDate = mfgDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public BigDecimal getMstProductPackType() {
        return mstProductPackType;
    }

    public void setMstProductPackType(BigDecimal mstProductPackType) {
        this.mstProductPackType = mstProductPackType;
    }
}
