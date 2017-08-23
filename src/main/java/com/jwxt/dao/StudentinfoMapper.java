package com.jwxt.dao;

import com.jwxt.common.ServerResponse;
import com.jwxt.pojo.Studentinfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentinfoMapper {
    int insert(Studentinfo record);

    int insertSelective(Studentinfo record);

    List<Studentinfo> selectstuinfoByPrimaryKey(@Param("studentId") Integer studentId);

    int delstuinfo(@Param("studentId") Integer studentId,@Param("classId") String classId);

    List<Studentinfo> findall();
}