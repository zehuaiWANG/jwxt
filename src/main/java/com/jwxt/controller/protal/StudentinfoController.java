package com.jwxt.controller.protal;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jwxt.common.ServerResponse;
import com.jwxt.pojo.Classinfo;
import com.jwxt.pojo.Studentinfo;
import com.jwxt.pojo.StudentinfoData;
import com.jwxt.service.IClassinfoService;
import com.jwxt.service.IStudentinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/student/")
public class StudentinfoController {
    @Autowired
    private IStudentinfoService iStudentinfoService;

    @Autowired
    private IClassinfoService iClassinfoService;
    @RequestMapping(value ="selectstuinfoByPrimaryKey.do",method = RequestMethod.POST)
    @ResponseBody
    public List<StudentinfoData> selectstuinfoByPrimaryKey(Integer studentid){
        List<Studentinfo> list1 =  iStudentinfoService.selectStudnetinfoByPrimaryKey(studentid);
        List<StudentinfoData> list2 = new ArrayList<>();

       for (int i = 0;i<list1.size();i++){
           String[] str = list1.get(i).getClassTime().split(",");
          for (int j=0;j<str.length;j++){
               StudentinfoData data = new StudentinfoData();
              data.setClassId(list1.get(i).getClassId());
              data.setStudentId(list1.get(i).getStudentId());
              data.setClassName(list1.get(i).getClassName());
               data.setWeek(str[j].split("_")[0]);
               data.setDay(str[j].split("_")[1]);
               data.setTime(str[j].split("_")[2]);
               data.setLocation(str[j].split("_")[3]);
               list2.add(data);
           }
       }
        return list2;
    }

    @RequestMapping(value ="insert.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> insertstuinfo(Integer studentid, String classid, String classtime) {
        return iStudentinfoService.insertstuinfo(studentid,classid,classtime);
    }

    @RequestMapping(value ="getallclassinfo.do",method = RequestMethod.POST)
    @ResponseBody
    public List<Classinfo> getallclassinfo(Integer studentid){
        List<Classinfo> list = iClassinfoService.findClassinfoList();
        for (int i=0;i<list.size();i++)
        list.get(i).setClassVacancies( new Integer(insertstuinfo(studentid,list.get(i).getClassId(),list.get(i).getClassLocation()).getStatus()));
        return list;
    }

    @RequestMapping(value ="insertstuinfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> insert(Integer studentid, String classid, String classname,String classtime) {
        return iStudentinfoService.insert(studentid,classid,classname,classtime);
    }

    @RequestMapping(value ="selectInfo.do",method = RequestMethod.POST)
    @ResponseBody
    public List<Classinfo> selectInfo(Integer studentid,String info){
        List<Classinfo> list = iClassinfoService.selectInfo(info);
        for (int i=0;i<list.size();i++)
            list.get(i).setClassVacancies( new Integer(insertstuinfo(studentid,list.get(i).getClassId(),list.get(i).getClassLocation()).getStatus()));
        return list;
    }

    @RequestMapping(value ="delstuinfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delstuinfo(Integer studentid,String classid){
        return iStudentinfoService.delstuinfo(studentid,classid);
    }

    @RequestMapping(value ="getallclassinfoByPageHelper.do",method = RequestMethod.POST)
    @ResponseBody
    public PageInfo fo(Integer studentid, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Classinfo> list = iClassinfoService.findClassinfoList();
        for (int i=0;i<list.size();i++)
            list.get(i).setClassVacancies( new Integer(insertstuinfo(studentid,list.get(i).getClassId(),list.get(i).getClassLocation()).getStatus()));
        PageInfo pageResult = new PageInfo(list);
        return pageResult;
    }
}
