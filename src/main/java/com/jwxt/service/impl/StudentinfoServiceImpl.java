package com.jwxt.service.impl;

import com.jwxt.common.ServerResponse;
import com.jwxt.dao.StudentinfoMapper;
import com.jwxt.pojo.Studentinfo;
import com.jwxt.service.IStudentinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("iStudentinfoServiceImpl")
public class StudentinfoServiceImpl implements IStudentinfoService {
    @Autowired
    private StudentinfoMapper studentinfoMapper;
    @Override
    public List<Studentinfo> selectStudnetinfoByPrimaryKey(Integer studentId) {
        return studentinfoMapper.selectstuinfoByPrimaryKey(studentId);
    }

    @Override
    public ServerResponse<String> insertstuinfo(Integer studentid, String classid, String classtime) {
        Studentinfo studentinfodemo = new Studentinfo();
        studentinfodemo.setStudentId(studentid);
        studentinfodemo.setClassTime(classtime);
        studentinfodemo.setClassId(classid);
        studentinfodemo.setCreateTime(new Date());
        studentinfodemo.setUpdateTime(new Date());
        List<Studentinfo> stuinfo = selectStudnetinfoByPrimaryKey(studentid);
        if(stuinfo.isEmpty()){
            studentinfoMapper.insert(studentinfodemo);
            return ServerResponse.createBySuccessMessage("选课成功");
        }
        String []STU2 = classtime.split(" ");
        String[] STU =null;
        boolean flag = true;
        for (int i = 0;i<stuinfo.size();i++) {
            //STU = stuinfo.get(i).getClassTime().split(" ");
            if(classid.equals(stuinfo.get(i).getClassId())) flag = false;
        }
        for (int i=0;i<stuinfo.size();i++) {
            STU = stuinfo.get(i).getClassTime().split(" ");
            for (int j = 0; j < STU.length; j++) {
                for (int k = 0; k < STU2.length; k++) {
                    if (STU[j].split("_")[1].equals(STU2[k].split("_")[1]) &&
                            STU[j].split("_")[2].equals(STU2[k].split("_")[2])) {
                        flag = false;
                    }
                }
            }
        }
        if(flag){
            studentinfoMapper.insert(studentinfodemo);
            return ServerResponse.createByErrorMessage("选课成功");
        }else {
            flag = true;
            return ServerResponse.createBySuccessMessage("选课失败");
        }
    }

    public ServerResponse<String> delstuinfo(Integer studentid, String classid){
    int result = studentinfoMapper.delstuinfo(studentid,classid);
    if (result>0) return ServerResponse.createBySuccessMessage("退课成功");
        return ServerResponse.createByErrorMessage("退课失败");
    }
}
