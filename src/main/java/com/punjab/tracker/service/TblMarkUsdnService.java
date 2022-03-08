package com.punjab.tracker.service;
import com.punjab.tracker.model.TblMarkUsdn;
import java.util.List;
public interface TblMarkUsdnService {
    public List<TblMarkUsdn> FindAll();
    public List<Object> allCeo();
    public int updateData(int count,String mfgindentno,String cassecode,String etn);
    public String updateData1();
}
