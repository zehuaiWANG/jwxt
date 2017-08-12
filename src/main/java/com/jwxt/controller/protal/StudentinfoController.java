package com.jwxt.controller.protal;

import com.jwxt.common.ServerResponse;
import com.jwxt.pojo.Classinfo;
import com.jwxt.pojo.Studentinfo;
import com.jwxt.service.IStudentinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/student/")
public class StudentinfoController {
    @Autowired
    private IStudentinfoService iStudentinfoService;
    @RequestMapping(value ="selectstuinfoByPrimaryKey.do",method = RequestMethod.POST)
    @ResponseBody
    public List<Studentinfo> selectstuinfoByPrimaryKey(Integer studentid){
        return iStudentinfoService.selectStudnetinfoByPrimaryKey(studentid);
    }

    @RequestMapping(value ="insertstuinfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> insertstuinfo(Integer studentid, String classid, String classtime) {
    return iStudentinfoService.insertstuinfo(studentid,classid,classtime);
    }

    @RequestMapping(value ="delstuinfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delstuinfo(Integer studentid,String classid){
        return iStudentinfoService.delstuinfo(studentid,classid);
    }
}
