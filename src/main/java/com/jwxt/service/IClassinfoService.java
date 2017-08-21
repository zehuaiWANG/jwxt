package com.jwxt.service;


import com.jwxt.pojo.Classinfo;

import java.util.List;

public interface IClassinfoService {
    List<Classinfo> findClassinfoList();
    List<Classinfo> selectInfo(String info);
    boolean checkClassinfo(String classid, String classname,String classtime);
}
