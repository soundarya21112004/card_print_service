package com.punjab.tracker.service;
import com.punjab.tracker.model.TblManfacturerIndentScheduler;
import com.punjab.tracker.repository.TblManfacturerIndentSchedulerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TblManfacturerIndentSchedulerServiceImpl implements TblManfacturerIndentSchedulerService {
    @Autowired
    private TblManfacturerIndentSchedulerRepository tblManfacturerIndentSchedulerRepository;
    @Override
    public List<TblManfacturerIndentScheduler> FindAll() {
        return tblManfacturerIndentSchedulerRepository.findAll();
    }
}
