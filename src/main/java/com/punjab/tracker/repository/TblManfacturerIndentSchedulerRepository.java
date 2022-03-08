package com.punjab.tracker.repository;
import com.punjab.tracker.model.TblManfacturerIndentScheduler;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TblManfacturerIndentSchedulerRepository extends JpaRepository<TblManfacturerIndentScheduler, Long> {
}
