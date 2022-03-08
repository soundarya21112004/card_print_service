package com.punjab.tracker.service;
import com.punjab.tracker.model.TblMarkUsdn;
import com.punjab.tracker.repository.TblMarkUsdnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TblMarkUsdnServiceImpl  implements TblMarkUsdnService{
    @Autowired
    private TblMarkUsdnRepository tblMarkUsdnRepository;
    @Override
    public List<TblMarkUsdn> FindAll() {
        return tblMarkUsdnRepository.findAll();
    }
    @Override
    public List<Object> allCeo() {
        return tblMarkUsdnRepository.findByTop(150,"00010");
    }
    @Override
    public int updateData(int count,String mfgindentno,String cassecode,String etn) {
         return tblMarkUsdnRepository.update(count,mfgindentno,cassecode,etn);
    }
    @Override
    public String updateData1() {
        tblMarkUsdnRepository.update1("63453745476883226765NXAHZVW8WAKCR","NXAHZVW8WAKCR","93946");
        return "1";
    }
}
