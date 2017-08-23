package com.jwxt.controller.protal;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jwxt.common.ConnectionCAS;
import com.jwxt.common.ResponseCode;
import com.jwxt.common.ServerResponse;
import com.jwxt.pojo.Classinfo;
import com.jwxt.pojo.ClassinfoData;
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
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/student/")
public class StudentinfoController {
    @Autowired
    private IStudentinfoService iStudentinfoService;
    @Autowired
    private IClassinfoService iClassinfoService;

    @RequestMapping(value="login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> login(String username, String password, HttpSession session){
        if (ConnectionCAS.getConnection(username,password)){
            session.setAttribute("username",username);
            return ServerResponse.createBySuccess("登录成功");
        }else {
            return  ServerResponse.createByErrorMessage("登陆失败");
        }
    }

    @RequestMapping(value ="logout.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute("username");
        return ServerResponse.createBySuccess("登出成功");
    }

    @RequestMapping(value ="selectstuinfoByPrimaryKey.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<StudentinfoData>> selectstuinfoByPrimaryKey(HttpSession session){
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        String username = session.getAttribute("username").toString();
        Integer studentid = Integer.parseInt(username);
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
               switch (str[j].split("_")[1]){
                   case "Mon":data.setClassTime("1");break;
                   case "Tues":data.setClassTime("2");break;
                   case "Wed":data.setClassTime("3");break;
                   case "Thur":data.setClassTime("4");break;
                   case "Fri":data.setClassTime("5");break;
                   case "Sat":data.setClassTime("6");break;
                   case "Sun":data.setClassTime("7");break;
                   case "Monday":data.setClassTime("1");break;
                   case "Tuesday":data.setClassTime("2");break;
                   case "Wednesday":data.setClassTime("3");break;
                   case "Thursday":data.setClassTime("4");break;
                   case "Friday":data.setClassTime("5");break;
                   case "Saturday":data.setClassTime("6");break;
                   case "Sunday":data.setClassTime("7");break;
                   case "Tue":data.setClassTime("2");break;
                   case "Thu":data.setClassTime("4");break;
                   default:data.setClassTime("0");break;
               }
               data.setTime(str[j].split("_")[2]);
               switch (str[j].split("_")[2]){
                   case "8:00-9:00":data.setClass_time_num(1);break;
                   case "9:00-10:00":data.setClass_time_num(2);break;
                   case "10:00-11:00":data.setClass_time_num(3);break;
                   case "11:00-12:00":data.setClass_time_num(4);break;
                   case "14:00-15:00":data.setClass_time_num(5);break;
                   case "15:00-16:00":data.setClass_time_num(6);break;
                   case "16:00-17:00":data.setClass_time_num(7);break;
                   case "17:00-18:00":data.setClass_time_num(8);break;
                   case "19:00-20:00":data.setClass_time_num(9);break;
                   case "20:00-21:00":data.setClass_time_num(10);break;
                   case "21:00-22:00":data.setClass_time_num(11);break;
                   case "8:00-10:00":data.setClass_time_num(12);break;
                   case "8:00-11:00":data.setClass_time_num(123);break;
                   case "8:00-12:00":data.setClass_time_num(1234);break;
                   case "9:00-11:00":data.setClass_time_num(23);break;
                   case "9:00-12:00":data.setClass_time_num(234);break;
                   case "10:00-12:00":data.setClass_time_num(34);break;
                   case "14:00-16:00":data.setClass_time_num(56);break;
                   case "14:00-17:00":data.setClass_time_num(567);break;
                   case "14:00-18:00":data.setClass_time_num(5678);break;
                   case "15:00-17:00":data.setClass_time_num(67);break;
                   case "15:00-18:00":data.setClass_time_num(678);break;
                   case "16:00-18:00":data.setClass_time_num(78);break;
                   case "19:00-21:00":data.setClass_time_num(910);break;
                   case "19:00-22:00":data.setClass_time_num(91011);break;
                   case "20:00-22:00":data.setClass_time_num(1011);break;
                   default:data.setClass_time_num(0);break;
               }
               data.setLocation(str[j].split("_")[3]);
               list2.add(data);
           }
       }
        return ServerResponse.createBySuccess("登录成功",list2);
    }

    @RequestMapping(value ="insert.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> insertstuinfo(HttpSession session, String classid, String classtime) {
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        String username = session.getAttribute("username").toString();
        Integer studentid = Integer.parseInt(username);
        return iStudentinfoService.insertstuinfo(studentid,classid,classtime);
    }

    @RequestMapping(value ="getallclassinfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Classinfo>> getallclassinfo(HttpSession session){
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        String username = session.getAttribute("username").toString();
        Integer studentid = Integer.parseInt(username);
        List<Classinfo> list = iClassinfoService.findClassinfoList();
        for (int i=0;i<list.size();i++)
        list.get(i).setClassVacancies( new Integer(insertstuinfo(session,list.get(i).getClassId(),list.get(i).getClassLocation()).getStatus()));
        return ServerResponse.createBySuccess(list);
    }

    @RequestMapping(value ="insertstuinfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> insert(HttpSession session, String classid, String classname,String classtime,
                                           @RequestParam(value = "pageNum",defaultValue="1") int pageNum,
                                                @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) {
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        String username = session.getAttribute("username").toString();
        Integer studentid = Integer.parseInt(username);
        if (!iClassinfoService.checkClassinfo(classid,classname,classtime)){
            return ServerResponse.createByErrorMessage("选课失败");
        }
        return iStudentinfoService.insert(session,classid,classname,classtime,pageNum,pageSize);
    }

    @RequestMapping(value ="selectInfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> selectInfo(HttpSession session,String info,
                                                      @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                                      @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        List<Classinfo> list3 = iClassinfoService.findClassinfoList();
        int size3 = list3.size();
        PageHelper.startPage(pageNum,pageSize);
        List<Classinfo> list = iClassinfoService.selectInfo(info);
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
            a.setClassTime(list.get(i).getClassTime());
            a.setClassVacancies(list.get(i).getClassVacancies());
            a.setOpento(list.get(i).getOpento());
            a.setConflit(new Integer(insertstuinfo(session,list.get(i).getClassId(),list.get(i).getClassLocation()).getStatus()));
            a.setNecessary(list.get(i).getNecessary());
            list2.add(a);
        }
        PageInfo pageResult = new PageInfo(list2);
        //这个算法可牛逼了
        pageResult.setSize((size3+pageSize-1)/pageSize);
        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        pageResult.setPrePage(pageNum-1);
        pageResult.setNextPage(pageNum+1);
        pageResult.setLastPage(pageResult.getSize());
        pageResult.setIsFirstPage(pageNum == 1);
        pageResult.setIsLastPage(pageNum == pageResult.getSize());
        pageResult.setHasNextPage(pageNum != pageResult.getSize());
        pageResult.setHasPreviousPage(pageNum != 1);
        return ServerResponse.createBySuccess(pageResult);
    }

    @RequestMapping(value ="delstuinfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> delstuinfo(HttpSession session,String classid,
                                             @RequestParam(value = "pageNum",defaultValue="1") int pageNum,
                                             @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        String username = session.getAttribute("username").toString();
        Integer studentid = Integer.parseInt(username);
        return iStudentinfoService.delstuinfo(session,classid,pageNum,pageSize);
    }

    @RequestMapping(value ="getallclassinfoByPageHelper.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> fo(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        String username = session.getAttribute("username").toString();
        Integer studentid = Integer.parseInt(username);
        List<Classinfo> list3 = iClassinfoService.findClassinfoList();
        int size3 = list3.size();
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
            a.setClassTime(list.get(i).getClassTime());
            a.setClassVacancies(list.get(i).getClassVacancies());
            a.setOpento(list.get(i).getOpento());
            a.setConflit(new Integer(insertstuinfo(session,list.get(i).getClassId(),list.get(i).getClassLocation()).getStatus()));
            a.setNecessary(list.get(i).getNecessary());
            list2.add(a);
        }
        PageInfo pageResult = new PageInfo(list2);
        //这个算法可牛逼了
        pageResult.setSize((size3+pageSize-1)/pageSize);
        pageResult.setPageNum(pageNum);
        pageResult.setPageSize(pageSize);
        pageResult.setPrePage(pageNum-1);
        pageResult.setNextPage(pageNum+1);
        pageResult.setLastPage(pageResult.getSize());
        pageResult.setIsFirstPage(pageNum == 1);
        pageResult.setIsLastPage(pageNum == pageResult.getSize());
        pageResult.setHasNextPage(pageNum != pageResult.getSize());
        pageResult.setHasPreviousPage(pageNum != 1);
        return ServerResponse.createBySuccess(pageResult);
    }

    @RequestMapping(value ="listStuinfo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<Studentinfo>> listStuinfo(HttpSession session){
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        String username = session.getAttribute("username").toString();
        Integer studentid = Integer.parseInt(username);
        return ServerResponse.createBySuccess(iStudentinfoService.selectStudnetinfoByPrimaryKey(studentid));
    }

    @RequestMapping(value ="studentid.do",method = RequestMethod.POST)
    @ResponseBody
    public int studentid(HttpSession session){
        if (session.getAttribute("username") == null){
            return 0;
        }
        String username = session.getAttribute("username").toString();
        Integer studentid = Integer.parseInt(username);
        return studentid;
    }
}
