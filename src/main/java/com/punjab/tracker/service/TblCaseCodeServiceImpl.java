package com.punjab.tracker.service;
import com.punjab.tracker.model.TblCaseCode;
import com.punjab.tracker.repository.TblCaseCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TblCaseCodeServiceImpl  implements TblCaseCodeService{
    @Autowired
    private TblCaseCodeRepository tblCaseCodeRepository;
    @Override
    public List<TblCaseCode> FindAll() {
        return tblCaseCodeRepository.findAll();
    }
}