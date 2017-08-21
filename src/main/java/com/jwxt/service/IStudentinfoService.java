package com.jwxt.service;

import com.github.pagehelper.PageInfo;
import com.jwxt.common.ServerResponse;
import com.jwxt.pojo.Studentinfo;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface IStudentinfoService {
    List<Studentinfo> selectStudnetinfoByPrimaryKey(Integer studentId);
    ServerResponse<String> insertstuinfo(Integer studentid, String classid, String classtime);
    ServerResponse<PageInfo> insert(HttpSession session, String classid, String classname, String classtime, int pageNum, int pageSize);
    ServerResponse<PageInfo> delstuinfo(HttpSession session, String classid, int pageNum,int pageSize);
}
