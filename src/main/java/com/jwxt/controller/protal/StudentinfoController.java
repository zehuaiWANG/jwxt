package com.jwxt.controller.protal;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jwxt.common.ServerResponse;
import com.jwxt.pojo.Classinfo;
import com.jwxt.pojo.ClassinfoData;
import com.jwxt.pojo.Studentinfo;
import com.jwxt.pojo.StudentinfoData;
import com.jwxt.service.IClassinfoService;
import com.jwxt.service.IStudentinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.*;

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
        System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();System.out.println();
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
               switch (str[j].split("_")[2]){
                   case "8:00-9:50":data.setClass_time_num(1);break;
                   case "8:00-10:00":data.setClass_time_num(1);break;
                   case "10:00-11:50":data.setClass_time_num(2);break;
                   case "10:00-12:00":data.setClass_time_num(2);break;
                   case "10:10-12:00":data.setClass_time_num(2);break;
                   case "8:00-11:50":data.setClass_time_num(12);break;
                   case "8:00-12:00":data.setClass_time_num(12);break;
                   case "14:00-15:50":data.setClass_time_num(3);break;
                   case "14:00-16:00":data.setClass_time_num(3);break;
                   case "16:00-17:50":data.setClass_time_num(4);break;
                   case "16:00-18:00":data.setClass_time_num(4);break;
                   case "16:10-18:00":data.setClass_time_num(4);break;
                   case "19:00-21:00":data.setClass_time_num(5);break;
                   case "19:00-20:50":data.setClass_time_num(5);break;
                   default:data.setClass_time_num(0);break;
               }
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
    public ServerResponse<PageInfo> insert(Integer studentid, String classid, String classname,String classtime,
                                           @RequestParam(value = "pageNum",defaultValue="1") int pageNum,
                                                @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) {
        return iStudentinfoService.insert(studentid,classid,classname,classtime,pageNum,pageSize);
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
    public ServerResponse<PageInfo> delstuinfo(Integer studentid,String classid,
                                             @RequestParam(value = "pageNum",defaultValue="1") int pageNum,
                                             @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        return iStudentinfoService.delstuinfo(studentid,classid,pageNum,pageSize);
    }

    @RequestMapping(value ="getallclassinfoByPageHelper.do",method = RequestMethod.POST)
    @ResponseBody
    public PageInfo fo(Integer studentid, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Classinfo> list = iClassinfoService.findClassinfoList();
        List<ClassinfoData>list2 = new ArrayList<ClassinfoData>();
        for(int i = 0;i<list.size();i++){
          ClassinfoData a = new ClassinfoData();
            a.setClassName(list.get(i).getClassName());
            a.setUpdateTime(list.get(i).getUpdateTime());
            a.setCreateTime(list.get(i).getCreateTime());
            a.setClassName(list.get(i).getClassName());
            a.setClassId(list.get(i).getClassId());
            a.setClassLocation(list.get(i).getClassLocation());
            a.setClassCredit(list.get(i).getClassCredit());
            a.setClassTutor(list.get(i).getClassTutor());
            a.setClassVacancies(list.get(i).getClassVacancies());
            a.setConflit(new Integer(insertstuinfo(studentid,list.get(i).getClassId(),list.get(i).getClassLocation()).getStatus()));
            a.setNecessary(list.get(i).getNecessary());
            list2.add(a);
        }
        /*for (int i=0;i<list.size();i++)
            list.get(i).setClassVacancies( new Integer(insertstuinfo(studentid,list.get(i).getClassId(),list.get(i).getClassLocation()).getStatus()));*/
        PageInfo pageResult = new PageInfo(list2);
        return pageResult;
    }
}
