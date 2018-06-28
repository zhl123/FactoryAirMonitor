package com.carryit.base.fam.controller;

import com.carryit.base.fam.bean.FaultRecords;
import com.carryit.base.fam.bean.User;
import com.carryit.base.fam.service.impl.FaultRecordsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Controller
public class FaultController {

    @Autowired
    private FaultRecordsService faultRecordsService;

    @RequestMapping("/faultManager")
    public ModelAndView alertManager(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (request.getSession().getAttribute("cuser") == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        User cuser = (User) request.getSession().getAttribute("cuser");
        modelAndView.addObject("cuser", cuser);
        List<FaultRecords> faultRecords = null;
//        if ("superAdmin".equalsIgnoreCase(cuser.getUserRole())) {
//           faultRecords = faultRecordsService.queryAllFaultRecords();
//        }else {
        FaultRecords faultRecord = new FaultRecords();
        faultRecord.setAppEui(request.getParameter("groupId"));
        faultRecords = faultRecordsService.queryFaultRecordsByAppEui(faultRecord);
//        }
        modelAndView.addObject("faultRecords", faultRecords);
        modelAndView.setViewName("new/faultManager");
        return modelAndView;
    }
}
