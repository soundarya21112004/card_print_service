package com.punjab.tracker.repository;

import com.punjab.tracker.model.TblMarkUsdn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface  TblMarkUsdnRepository extends JpaRepository<TblMarkUsdn, Long> {

   // @Query("SELECT  TOP 25 a FROM TblMarkUsdn a where a.barCode is null and a.manufacturerIndentNo='00010' and a.mstStatus='2'  ORDER BY a.markSlno ASC")
    @Query(nativeQuery = true,
            value = "SELECT TOP (:a) mark_slno FROM tbl_mark_usdn \n" +
                    "where BAR_CODE is null and \n" +
                    "MANUFACTURER_INDENT_NO = :b and \n" +
                    "MARK_STATUS_ID='2'  ORDER BY mark_slno ASC;")
    List<Object> findByTop(@Param("a") int a,@Param("b") String b);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(nativeQuery = true,
            value = " UPDATE tbl_mark_usdn " +
                    " SET BAR_CODE=:cassecode,ETN=:etn FROM " +
                    " (SELECT TOP (:count) mark_slno FROM tbl_mark_usdn " +
                    " where BAR_CODE is null and " +
                    " MANUFACTURER_INDENT_NO=:mfgindentno and" +
                    " MARK_STATUS_ID='2'  ORDER BY mark_slno ASC ) AS th  " +
                    " WHERE tbl_mark_usdn.mark_slno = th.mark_slno")
    public int update(@Param("count") int count,@Param("mfgindentno") String mfgindentno,@Param("cassecode") String cassecode,@Param("etn") String etn);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(nativeQuery = true,
            value = "UPDATE tbl_mark_usdn " +
                    "SET BAR_CODE=:cassecode,ETN=:etn  " +
                    "WHERE tbl_mark_usdn.mark_slno = :markSlNo")
    public int update1(@Param("cassecode") String cassecode,@Param("etn") String etn,@Param("markSlNo") String markSlNo);



}
