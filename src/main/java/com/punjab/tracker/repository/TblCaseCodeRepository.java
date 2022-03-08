package com.punjab.tracker.repository;

import com.punjab.tracker.model.TblCaseCode;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TblCaseCodeRepository extends JpaRepository<TblCaseCode, Long> {


}
