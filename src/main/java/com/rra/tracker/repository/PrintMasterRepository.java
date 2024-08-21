package com.rra.tracker.repository;

import com.rra.tracker.model.master.PrintDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PrintMasterRepository extends JpaRepository<PrintDTO, Long> {
    List<PrintDTO> findByStatus(long name);
//
//    // @Query("SELECT  TOP 25 a FROM TblMarkUsdn a where a.barCode is null and a.manufacturerIndentNo='00010' and a.mstStatus='2'  ORDER BY a.markSlno ASC")
//    @Query(nativeQuery = true,
//            value = "SELECT TOP (:a) usdn_no FROM tbl_mark_usdn ;")
//    List<Object> findByTop(@Param("a") int a);
//
//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query(nativeQuery = true,
//            value = " UPDATE tbl_mark_usdn " +
//                    " SET entered_by='00001' " +
//                    " WHERE usdn_no='IAA3718102'")
//    public int update(@Param("count") int count, @Param("mfgindentno") String mfgindentno, @Param("cassecode") String cassecode, @Param("etn") String etn);
//
//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query(nativeQuery = true,
//            value = "UPDATE tbl_mark_usdn " +
//                    "SET BAR_CODE=:cassecode,ETN=:etn  " +
//                    "WHERE tbl_mark_usdn.mark_slno = :markSlNo")
//    public int update1(@Param("cassecode") String cassecode, @Param("etn") String etn, @Param("markSlNo") String markSlNo);
//
//    @Query(nativeQuery = true,
//            value = "select t40.MANUFACTURERNAME_EN,t40.MANUFACTURER_ID,t40.TIN_NO," +
//                    "t40.BUSINESSTYPE_ID,case when t100.act is null then 0 else t100.act end activated ," +
//                    "case when t100.dam is null then 0 else t100.dam end damaged ," +
//                    "case when t100.missing is null then 0 else t100.missing end missing ," +
//                    "case when t100.counterfeit is null then 0 else t100.counterfeit end counterfeit ," +
//                    "case when t100.duplicate is null then 0 else t100.duplicate end duplicate," +
//                    "t100.indent,t100.statDate,t100.prodcode," +
//                    "t100.statusmodifyby from MST_MANUFACTURER t40 left join " +
//                    "(SELECT MFG,MFG_ID,tinNo,bistype_id,indent,ACTIVATED_COUNT1 as act," +
//                    "DAMAGED_COUNT1 as dam,MISSING_COUNT1 as missing,COUNTERFIET_COUNT1 as counterfeit," +
//                    "DUPLICATE_COUNT1 as duplicate,bistype_id AS bistypeid,prod_code AS prodcode," +
//                    "statusmodify AS statusmodifyby, TO_CHAR (status_date, 'YYYY/MM/DD')  AS statDate " +
//                    "FROM (select mm.MANUFACTURERNAME_EN AS MFG,mm.manufacturer_id AS MFG_ID," +
//                    "mm.tin_no AS tinNo,mm.businesstype_id AS bistype_id,t1.manufacturer_indent_no AS indent," +
//                    "t1.PRODUCT_CODE AS prod_code,t1.STATUS_MODIFIED_BY AS statusmodify," +
//                    "t1.mark_status_id  as mark_status_id, " +
//                    "trunc(t1.STATUS_MODIFIED_DATE) AS status_date from tbl_mark_usdn t1 " +
//                    "inner JOIN MST_MANUFACTURER mm ON mm.MANUFACTURER_ID =t1.manufacturer_id " +
//                    "where t1.status_modified_date  between :fromdate  and :todate ) " +
//                    "pivot (count(*) as count1 for mark_status_id in " +
//                    "('3' AS  ACTIVATED ,'16' AS  DAMAGED ,'155' as MISSING,'36' as COUNTERFIET,5 DUPLICATE)) " +
//                    "ORDER BY MFG_ID )t100 on t100.MFG_ID=t40.MANUFACTURER_ID " +
//                    "WHERE t40.activestatus='Y' " +
//                    "ORDER BY t40.MANUFACTURER_ID")
//    List<Object> loadAllQtyByCategorywithDispatch(@Param("fromdate") String fromdate,@Param("todate") String todate);
//    @Query(nativeQuery = true,
//            value = "select t40.MANUFACTURERNAME_EN,t40.MANUFACTURER_ID,t40.TIN_NO," +
//                    "t40.BUSINESSTYPE_ID,case when t100.act is null then 0 else t100.act end activated ," +
//                    "case when t100.dam is null then 0 else t100.dam end damaged ," +
//                    "case when t100.missing is null then 0 else t100.missing end missing ," +
//                    "case when t100.counterfeit is null then 0 else t100.counterfeit end counterfeit ," +
//                    "case when t100.duplicate is null then 0 else t100.duplicate end duplicate," +
//                    "t100.indent,t100.disDate,t100.quantity,t100.statDate,t100.prodcode," +
//                    "t100.statusmodifyby from MST_MANUFACTURER t40 left join " +
//                    "(SELECT MFG,MFG_ID,tinNo,bistype_id,indent,disDate,quantity,ACTIVATED_COUNT1 as act," +
//                    "DAMAGED_COUNT1 as dam,MISSING_COUNT1 as missing,COUNTERFIET_COUNT1 as counterfeit," +
//                    "DUPLICATE_COUNT1 as duplicate,bistype_id AS bistypeid,prod_code AS prodcode," +
//                    "statusmodify AS statusmodifyby, TO_CHAR (status_date, 'YYYY/MM/DD')  AS statDate " +
//                    "FROM (select mm.MANUFACTURERNAME_EN AS MFG,mm.manufacturer_id AS MFG_ID," +
//                    "mm.tin_no AS tinNo,mm.businesstype_id AS bistype_id,t1.manufacturer_indent_no AS indent," +
//                    "t1.PRODUCT_CODE AS prod_code,t1.STATUS_MODIFIED_BY AS statusmodify," +
//                    "t5.DISPATCH_DATE AS disDate,t5.TOTAL_MARKS AS quantity,t1.mark_status_id  as mark_status_id, " +
//                    "trunc(t1.STATUS_MODIFIED_DATE) AS status_date from tbl_mark_usdn t1 " +
//                    "inner JOIN MST_MANUFACTURER mm ON mm.MANUFACTURER_ID =t1.manufacturer_id " +
//                    "INNER JOIN TBL_DISPATCH_TO_MANUFACTURER t5  ON t5.INDENT_NO =t1.MANUFACTURER_INDENT_NO " +
//                    "where t1.status_modified_date  between :fromdate  and :todate ) " +
//                    "pivot (count(*) as count1 for mark_status_id in " +
//                    "('3' AS  ACTIVATED ,'16' AS  DAMAGED ,'155' as MISSING,'36' as COUNTERFIET,5 DUPLICATE)) " +
//                    "ORDER BY MFG_ID )t100 on t100.MFG_ID=t40.MANUFACTURER_ID " +
//                    "WHERE t40.activestatus='Y' " +
//                    "ORDER BY t40.MANUFACTURER_ID")
//    List<Object> loadAllQtyByCategory(@Param("fromdate") String fromdate,@Param("todate") String todate);
//
//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query(nativeQuery = true,
//            value = "INSERT INTO TBL_STOCK_SUMMARY_SCHEDULER" +
//                    "(PDT_CATEGORY, INDENT_QTY, BALANCE_QTY, APPROVED_QTY, UPDATED_BY,  ACTIVE_STATUS)" +
//                    "VALUES(:catId, :totalQty,:balanceQty, :approvedQty, 'SYSTEM','1')")
//    public int updateStockDetails(@Param("catId") BigDecimal catId, @Param("totalQty") BigDecimal totalQty,
//                                  @Param("balanceQty") BigDecimal balanceQty, @Param("approvedQty") BigDecimal approvedQty);
//
// @Modifying(clearAutomatically = true)
// @Transactional
// @Query(nativeQuery = true,
//         value = "UPDATE TBL_STOCK_SUMMARY_SCHEDULER " +
//                 "SET active_status='0'")
// public int deactiveStock();
//
//
//    @Query(nativeQuery = true,
//            value = "SELECT tmu.MANUFACTURER_INDENT_NO,tmi.ENTERED_DATE ,tmi.QUANTITY AS STOCK_RECEIVED," +
//                    "count(tmu.USDN_NO),tmu.PRODUCT_CODE,\n" +
//                    "tp.PRODUCTNAME_EN ,tp.VOLUME,mu.UNIT_NAME ,tp.PRICE ,count(tmu.USDN_NO)*tp.PRICE," +
//                    "trunc(tmu.STATUS_MODIFIED_DATE),mm.MANUFACTURER_ID,mm.MANUFACTURERNAME_EN ,mm.TIN_NO " +
//                    "FROM TBL_MARK_USDN tmu \n" +
//                    "INNER JOIN TBL_MANUFACTURER_INDENT tmi ON tmi.MANUFACTURER_INDENT_NO =tmu.MANUFACTURER_INDENT_NO \n" +
//                    "INNER JOIN MST_MANUFACTURER mm ON mm.MANUFACTURER_ID =tmi.MANUFACTURER_ID \n" +
//                    "INNER join MST_BUSINESSTYPE mb ON mb.BUSINESSTYPE_ID =mm.BUSINESSTYPE_ID \n" +
//                    "INNER JOIN MST_PRODUCT tp ON tp.PRODUCT_CODE =tmu.PRODUCT_CODE \n" +
//                    "INNER JOIN MST_UNIT mu ON mu.UNIT_ID =tp.UNIT \n" +
//                    "left join TBL_STOCK_INWARDDETAILS tsi ON tsi.MANUFACTURER_INDENT_NO =tmu.MANUFACTURER_INDENT_NO " +
//                    "where \n" +
//                    "tmu.PRODUCT_CODE IS NOT NULL AND tmu.MARK_STATUS_ID ='3' \n" +
//                    "AND tmu.STATUS_MODIFIED_DATE BETWEEN :fromdate AND :todate \n" +
//                    "GROUP BY tmu.MANUFACTURER_INDENT_NO,tmi.ENTERED_DATE ,tmi.QUANTITY,tmi.QUANTITY,tmu.PRODUCT_CODE,\n" +
//                    "tp.PRODUCTNAME_EN ,tp.VOLUME,mu.UNIT_NAME ,tp.PRICE ,trunc(tmu.STATUS_MODIFIED_DATE)," +
//                    "mm.MANUFACTURER_ID,mm.MANUFACTURERNAME_EN ,mm.TIN_NO")
//    List<Object> loadStockDetails(@Param("fromdate") String fromdate,@Param("todate") String todate);
//
    @Query(nativeQuery = true,
            value = "SELECT json_data FROM print where status='0' ")
    public ArrayList<String> loadListOfJsonData();
//
//    @Query(nativeQuery = true,
//            value = "SELECT tdtm.INDENT_NO,tdtm.DISPATCH_ID,tdtm.TOTAL_MARKS FROM TBL_DISPATCH_TO_MANUFACTURER tdtm WHERE STOCK_STATUS =:status AND " +
//                    "MANUFACTURER_ID in :userlist and active='1'")
//    List<Object> getDispatchList(@Param("status") String status,@Param("userlist") List<String> userlist);
//
//    @Query(nativeQuery = true,
//            value = "select count(*) as max from print")
//    Integer getSamplewsMax();
//
//
//    @Query(nativeQuery = true,
//            value = "select mark_slno_id as startsno,(usdn_no || '~' || usdn_no_encrypt || '~' || mark_slno_id) as startusdn " +
//                    "from tbl_mark_usdn where MANUFACTURER_INDENT_NO =:indentno AND file_id= :fileid order by mark_slno_id")
//    List<Object> getUsdnList(@Param("indentno") String indentno,@Param("fileid") Integer fileid);
//
//
//
//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query(nativeQuery = true,
//            value = "UPDATE TBL_DISPATCH_TO_MANUFACTURER " +
//                    "SET STOCK_STATUS='8' where DISPATCH_ID=:dispatchid")
//    public void updatePickUpStatus(@Param("dispatchid") String dispatchid);
//
//
//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query(nativeQuery = true,
//            value = "UPDATE TBL_DISPATCH_TO_MANUFACTURER " +
//                    "SET active='2' where DISPATCH_ID=:dispatchid")
//    public void updateAfterCSV(@Param("dispatchid") String dispatchid);
//
//
//    @Query(nativeQuery = true,
//            value = "SELECT count(*) FROM TBL_MARK_USDN tmu WHERE MANUFACTURER_INDENT_NO =:indentNo AND mark_status_id='3'")
//    Integer loadAvailableStock(@Param("indentNo") String indentNo);
//
//    @Query(nativeQuery = true,
//            value = "SELECT count(*) FROM TBL_STAMP_USAGE tmu WHERE created_date between :currentdateFrom and :currentdateTo")
//    Integer loadStampUsageForCheck(@Param("currentdateFrom") String currentdateFrom,@Param("currentdateTo") String currentdateTo);


}
