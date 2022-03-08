package com.punjab.tracker.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "TBL_MANUFACTURER_INDENT_SCHEDULER")
public class TblManfacturerIndentScheduler {

    @Id
    @Column(name = "ID")
    private long id;
    @Column(name = "MANUFACTURER_INDENT_NO")
    private String manufacturerIndentNo;
    @Column(name = "QUANTITY")
    private BigDecimal quantity;
    @Column(name = "CURRENT_COUNT")
    private String currentCount;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturerIndentNo() {
        return manufacturerIndentNo;
    }

    public void setManufacturerIndentNo(String manufacturerIndentNo) {
        this.manufacturerIndentNo = manufacturerIndentNo;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(String currentCount) {
        this.currentCount = currentCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
