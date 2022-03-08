package com.punjab.tracker.controller;
import com.punjab.tracker.model.TblCaseCode;
import com.punjab.tracker.model.TblManfacturerIndentScheduler;
import com.punjab.tracker.model.TblManufacturerIndent;
import com.punjab.tracker.model.TblMarkUsdn;
import com.punjab.tracker.service.TblCaseCodeService;
import com.punjab.tracker.service.TblManfacturerIndentSchedulerService;
import com.punjab.tracker.service.TblManufacturerIndentService;
import com.punjab.tracker.service.TblMarkUsdnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/api")
public class CaseCodeRequestController {
    @Autowired
    private TblMarkUsdnService tblMarkUsdnService;
    @Autowired
    private TblManufacturerIndentService tblManufacturerIndentService;
    @Autowired
    private TblCaseCodeService tblCaseCodeService;
    @Autowired
    private TblManfacturerIndentSchedulerService tblManfacturerIndentSchedulerService;
    @GetMapping("tblMarkUsdn")
    public List<TblMarkUsdn> getcovidCategeoryDownloadList() {
        return tblMarkUsdnService.FindAll();
    }
    @GetMapping("allCeo")
    public List<Object> getallCeo() {
        return tblMarkUsdnService.allCeo();
    }
    @GetMapping("tblManfacturerIndentScheduler")
    public List<TblManfacturerIndentScheduler> getTblManfacturerIndentScheduler() {
        return tblManfacturerIndentSchedulerService.FindAll();
    }
    @GetMapping("allUpdate")
    public int getallUpdate() {
         return tblMarkUsdnService.updateData(150,"00010","63453745476883226765NXAHZVW8WAKCR","NXAHZVW8WAKCR");
    }
    @GetMapping("allUpdate1")
    public String getallUpdate1() {
        tblMarkUsdnService.updateData1();
        return "1";
    }
    @GetMapping("tblManufacturerIndent")
    public List<TblManufacturerIndent> tblManufacturerIndentRepository() {
        return tblManufacturerIndentService.FindAll();
    }
    @GetMapping("tblCaseCode")
    public List<TblCaseCode> tblCaseCodeRepository() {
        return tblCaseCodeService.FindAll();
    }
}