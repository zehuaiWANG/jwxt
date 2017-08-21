package com.jwxt.dao;

import com.jwxt.pojo.Classinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassinfoMapper {
    int deleteByPrimaryKey(String classId);

    int insert(Classinfo record);

    int insertSelective(Classinfo record);

    Classinfo selectByPrimaryKey(String classId);

    int updateByPrimaryKeySelective(Classinfo record);

    int updateByPrimaryKey(Classinfo record);

    List<Classinfo> selectAllClassInfo();

    List<Classinfo> selectInfo(String info);

    int selectByClassidAndClassnameAndClasstime(@Param("classid") String classid,@Param("classname") String classname,@Param("classtime") String classtime);
}