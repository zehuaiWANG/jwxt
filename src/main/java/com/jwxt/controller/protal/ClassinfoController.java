package com.jwxt.controller.protal;

import com.jwxt.pojo.Classinfo;
import com.jwxt.service.IClassinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/class/")
public class ClassinfoController {
    @Autowired
    private IClassinfoService iClassinfoService;

    @RequestMapping(value ="getallclassinfo.do",method = RequestMethod.GET)
    @ResponseBody
    public List<Classinfo> getallclassinfo(){
        List<Classinfo> list = iClassinfoService.findClassinfoList();
        return list;
    }

    @RequestMapping(value ="selectInfo.do",method = RequestMethod.POST)
    @ResponseBody
    public List<Classinfo> selectInfo(String info){
        List<Classinfo> list = iClassinfoService.selectInfo(info);
        return list;
    }

}
