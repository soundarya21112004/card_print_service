package com.rra.tracker.service;
import com.rra.tracker.model.slave.SampleWs;
import com.rra.tracker.repository.PrintSlaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
public class TblMarkUsdnServiceImpl implements TblMarkUsdnService{

    private PrintSlaveRepository tblMarkUsdnRepository;

    @Override
    public Integer getSamplewsMax() {
        return tblMarkUsdnRepository.getSamplewsMax();
    }
}
