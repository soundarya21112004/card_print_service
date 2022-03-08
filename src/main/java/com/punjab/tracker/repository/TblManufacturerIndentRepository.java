package com.punjab.tracker.repository;

import com.punjab.tracker.model.TblManufacturerIndent;
import com.punjab.tracker.model.TblMarkUsdn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TblManufacturerIndentRepository extends JpaRepository<TblManufacturerIndent, Long> {


}
