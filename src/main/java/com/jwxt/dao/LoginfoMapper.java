package com.jwxt.dao;

import com.jwxt.pojo.Loginfo;

import java.util.List;

public interface LoginfoMapper {
    int deleteByPrimaryKey(Integer studentId);

    int insert(Loginfo record);

    int insertSelective(Loginfo record);

    Loginfo selectByPrimaryKey(Integer studentId);

    int updateByPrimaryKeySelective(Loginfo record);

    int updateByPrimaryKey(Loginfo record);

    List<Loginfo> selectAllLogInfo();
}