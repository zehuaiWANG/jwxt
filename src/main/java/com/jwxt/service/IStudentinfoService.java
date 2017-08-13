package com.jwxt.service;

import com.jwxt.common.ServerResponse;
import com.jwxt.pojo.Studentinfo;

import java.util.List;

public interface IStudentinfoService {
    List<Studentinfo> selectStudnetinfoByPrimaryKey(Integer studentId);
    ServerResponse<String> insertstuinfo(Integer studentid, String classid, String classtime);
    ServerResponse<String> insert(Integer studentid, String classid,String classname, String classtime);
    ServerResponse<String> delstuinfo(Integer studentid, String classid);
}
