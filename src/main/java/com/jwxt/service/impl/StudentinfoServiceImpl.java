package com.jwxt.service.impl;

import com.github.pagehelper.PageInfo;
import com.jwxt.common.ServerResponse;
import com.jwxt.controller.protal.StudentinfoController;
import com.jwxt.dao.LoginfoMapper;
import com.jwxt.dao.StudentinfoMapper;
import com.jwxt.pojo.Loginfo;
import com.jwxt.pojo.Studentinfo;
import com.jwxt.service.IStudentinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service("iStudentinfoService")
public class StudentinfoServiceImpl implements IStudentinfoService{
    @Autowired
    private StudentinfoMapper studentinfoMapper;

    @Autowired
    private LoginfoMapper loginfoMapper;

    @Autowired
     private StudentinfoController b;
    @Override
    public List<Studentinfo> selectStudnetinfoByPrimaryKey(Integer studentId) {
        List<Studentinfo> stu = studentinfoMapper.selectstuinfoByPrimaryKey(studentId);
        return stu;
    }

    @Override
    public ServerResponse<String> insertstuinfo(Integer studentid, String classid, String classtime){
        List<Studentinfo> stu = studentinfoMapper.selectstuinfoByPrimaryKey(studentid);
        boolean flag = false;
        String []strflag = classtime.split(",");
        for (int i = 0;i<stu.size();i++){
            String []str = stu.get(i).getClassTime().split(",");
            for (int j=0;j<str.length;j++){
                  for (int k=0;k<strflag.length;k++){
                      if (
                              str[j].split("_")[1].equals(strflag[k].split("_")[1])&&
                                      str[j].split("_")[2].equals(strflag[k].split("_")[2])&&
                                      Integer.parseInt(str[j].split("_")[0])+Integer.parseInt(strflag[k].split("_")[0])!=3
                              ) flag = true;
                  }
            }
        }
        if (flag)  return ServerResponse.createErrorCodeMessage(1,"课程冲突");
        else return ServerResponse.createBySuccessMessage("课程不冲突");
    }

    public ServerResponse<PageInfo> insert(HttpSession session, String classid, String classname, String classtime, int pageNum, int pageSize){
        String username = session.getAttribute("username").toString();
        Integer studentid = Integer.parseInt(username);
        Studentinfo studentinfodemo = new Studentinfo();
        studentinfodemo.setStudentId(studentid);
        studentinfodemo.setClassTime(classtime);
        studentinfodemo.setClassName(classname);
        studentinfodemo.setClassId(classid);
        studentinfodemo.setCreateTime(new Date());
        studentinfodemo.setUpdateTime(new Date());
        Loginfo loginfo = new Loginfo();
        loginfo.setClassId(classid);
        loginfo.setStudentId(studentid);
        loginfo.setOperate(1);
        loginfo.setUpdateTime(new Date());
        if (insertstuinfo(studentid,classid,classtime).isSuccess()){
            loginfoMapper.insert(loginfo);
            studentinfoMapper.insert(studentinfodemo);
           /* PageInfo a = new StudentinfoController().fo(studentid,pageNum,pageSize);*/
            return ServerResponse.createBySuccess("选课成功", b.fo(session,pageNum,pageSize).getData());
        }
        return ServerResponse.createBySuccess("课程冲突",b.fo(session,pageNum,pageSize).getData());
    }

    public ServerResponse<PageInfo> delstuinfo(HttpSession session, String classid, int pageNum,int pageSize){
        String username = session.getAttribute("username").toString();
        Integer studentid = Integer.parseInt(username);
        int result = studentinfoMapper.delstuinfo(studentid,classid);
        Loginfo loginfo = new Loginfo();
        loginfo.setClassId(classid);
        loginfo.setStudentId(studentid);
        loginfo.setOperate(0);
        loginfo.setUpdateTime(new Date());
        if (result>0){
            loginfoMapper.insert(loginfo);
            return ServerResponse.createBySuccess("退课成功", b.fo(session,pageNum,pageSize).getData());
        }
        return ServerResponse.createByErrorMessage("退课失败");
    }
}
