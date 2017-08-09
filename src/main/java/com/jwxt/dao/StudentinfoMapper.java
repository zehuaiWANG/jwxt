package com.jwxt.dao;

import com.jwxt.pojo.Studentinfo;

import java.util.List;

public interface StudentinfoMapper {
    int deleteByPrimaryKey(Integer studentId);

    int insert(Studentinfo record);

    int insertSelective(Studentinfo record);

    Studentinfo selectByPrimaryKey(Integer studentId);

    int updateByPrimaryKeySelective(Studentinfo record);

    int updateByPrimaryKey(Studentinfo record);

    List<Studentinfo> selectstuinfoByPrimaryKey(Integer studentId);
}