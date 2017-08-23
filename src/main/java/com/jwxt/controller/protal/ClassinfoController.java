package com.jwxt.controller.protal;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jwxt.common.ResponseCode;
import com.jwxt.common.ServerResponse;
import com.jwxt.pojo.Classinfo;
import com.jwxt.service.IClassinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/class/")
public class ClassinfoController {
    @Autowired
    private IClassinfoService iClassinfoService;

    @Autowired
    private StudentinfoController studentinfoController;
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

    @RequestMapping(value ="insert.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> insertClassinfo(HttpSession session,String classid, String classname, Integer credit
        , String tutor, String time, String location, Integer vancancies,Integer opento,Integer necessary){
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if (check(session,classid,classname,credit,tutor,time,location,vancancies,opento).getStatus() != 0){
            return ServerResponse.createByErrorMessage("请检查");
        }
        if (studentinfoController.isAdmin(session).getData().equals("1")) {
            Classinfo info = new Classinfo();
            info.setClassVacancies(vancancies);
            info.setClassCredit(credit);
            info.setClassId(classid);
            info.setClassLocation(location);
            info.setClassName(classname);
            info.setClassTutor(tutor);
            info.setClassTime(time);
            info.setOpento(opento);
            info.setNecessary(necessary);
            info.setCreateTime(new Date());
            info.setUpdateTime(new Date());
            return iClassinfoService.insert(info);
        }
        return ServerResponse.createByErrorMessage("请登录管理员");
    }

    @RequestMapping(value ="delet.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delectinfo(HttpSession session,String classid, String classname){
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if (studentinfoController.isAdmin(session).getData().equals("1")) {
            return iClassinfoService.delet(classname,classid);
        }
        return ServerResponse.createByErrorMessage("请登录管理员");
    }

    @RequestMapping(value ="getallo.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> getall(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if (studentinfoController.isAdmin(session).getData().equals("1")) {
            List<Classinfo> list3 = iClassinfoService.findClassinfoList();
            int size3 = list3.size();
            PageHelper.startPage(pageNum, pageSize);
            List<Classinfo> list = iClassinfoService.findClassinfoList();
            PageInfo pageResult = new PageInfo(list);
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
        return ServerResponse.createByErrorMessage("请登录管理员");
    }

    @RequestMapping(value ="check.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> check(HttpSession session,String classid, String classname,
                                        Integer credit
            , String tutor, String time, String location, Integer vancancies,Integer opento){
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if(iClassinfoService.selectByIdAndName(classid,classname)){
            return ServerResponse.createByErrorMessage("已存在该课程和id");
        }
        if (studentinfoController.isAdmin(session).getData().equals("1")) {
            String list[] = location.split(";");
            for (int i= 0;i < list.length;i++){
                String list2[] = list[i].split("_");
                for (int j=0;j<list2.length;j++){
                    if (list2.length < 3){
                        return ServerResponse.createByErrorMessage("Time&Venue错得太离谱了");
                    }
                    if (!(list2[0].equals("1")||list2[0].equals("2")||list2[0].equals("3"))){
                        System.out.println(list2[0]);
                        return ServerResponse.createByErrorMessage("Time&Venue第一个字段出错");
                    }
                    if (!(list2[1].equals("Mon")
                            ||list2[1].equals("Tues")
                            ||list2[1].equals("Wed")
                            ||list2[1].equals("Thur")
                            ||list2.equals("Fri")
                            ||list2[i].equals("Sat")
                            ||list2[1].equals("Sun"))){
                        return ServerResponse.createByErrorMessage("Time&Venue第二个字段出错");
                    }
                    if (!(list2[2].equals("8:00-9:00")
                            ||list2[2].equals("9:00-10:00")  || list2[2].equals("10:00-11:00")
                            ||list2[2].equals("11:00-12:00") || list2[2].equals("14:00-15:00")
                            ||list2[2].equals("16:00-17:00") || list2[2].equals("17:00-18:00")
                            ||list2[2].equals("19:00-20:00") || list2[2].equals("20:00-21:00")
                            ||list2[2].equals("21:00-22:00") || list2[2].equals("8:00-10:00")
                            ||list2[2].equals("8:00-11:00")  || list2[2].equals("8:00-12:00")
                            ||list2[2].equals("9:00-11:00")  || list2[2].equals("9:00-12:00")
                            ||list2[2].equals("10:00-12:00") || list2[2].equals("14:00-16:00")
                            ||list2[2].equals("14:00-17:00") || list2[2].equals("14:00-18:00")
                            ||list2[2].equals("15:00-17:00") || list2[2].equals("15:00-18:00")
                            ||list2[2].equals("16:00-18:00") || list2[2].equals("19:00-21:00")
                            ||list2[2].equals("19:00-22:00") || list2[2].equals("20:00-22:00"))){
                        return ServerResponse.createByErrorMessage("Time&Venue第三个字段出错");
                    }
                }
                return ServerResponse.createBySuccess("验证成功");
            }
            if (!(opento<3000&&opento>2011)){
                return ServerResponse.createByErrorMessage("opento出错");
            }
        }
        return ServerResponse.createByErrorMessage("请登录管理员");
    }

    //这又是一个牛逼的算法
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
