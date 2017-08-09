package com.jwxt.service;

import com.jwxt.common.ServerResponse;
import com.jwxt.pojo.Studentinfo;
import java.util.List;
public interface IStudentinfoService {
    List<Studentinfo> selectStudnetinfoByPrimaryKey(Integer studentId);
   // void insertstuinfo(Integer studentid, String classid,String classtime);
    ServerResponse<String> insertstuinfo(Integer studentid, String classid,String classtime);
}
