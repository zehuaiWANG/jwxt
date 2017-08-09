package com.jwxt.controller.protal;

import com.jwxt.pojo.Classinfo;
import com.jwxt.service.IClassinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
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

    @RequestMapping(value ="getpageclassinfo.do",method = RequestMethod.POST)
    @ResponseBody
    public List<Classinfo> getpageclassinfo(Integer page){
        List<Classinfo> list = iClassinfoService.findClassinfoList();
        List<Classinfo> list2 = new ArrayList<Classinfo>();
        //list2.add(list.get(1));
        if (list.size()<(page*15)){
         for (int i = (page-1)*15;i<list.size()-(page-1)*15;i++)
             list2.add(list.get(i));
        }else{
            for (int i = (page-1)*15;i<15*page;i++)
                list2.add(list.get(i));
        }
        return list2;
    }
}
