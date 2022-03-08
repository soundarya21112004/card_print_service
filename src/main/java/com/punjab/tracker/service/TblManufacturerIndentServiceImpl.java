package com.punjab.tracker.service;
import com.punjab.tracker.model.TblManufacturerIndent;
import com.punjab.tracker.repository.TblManufacturerIndentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TblManufacturerIndentServiceImpl implements TblManufacturerIndentService{
    @Autowired
    private TblManufacturerIndentRepository tblManufacturerIndentRepository;
    @Override
    public List<TblManufacturerIndent> FindAll() {
        return tblManufacturerIndentRepository.findAll();
    }
}
