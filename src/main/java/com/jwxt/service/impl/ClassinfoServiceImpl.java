package com.jwxt.service.impl;



import com.jwxt.dao.ClassinfoMapper;
import com.jwxt.pojo.Classinfo;
import com.jwxt.service.IClassinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("iClassinfoService")
public class ClassinfoServiceImpl implements IClassinfoService{

    @Autowired
    private ClassinfoMapper classinfoMapper;

    @Override
    public List<Classinfo> findClassinfoList() {
        List<Classinfo>list = classinfoMapper.selectAllClassInfo();
        return list;
    }

    @Override
    public List<Classinfo> selectInfo(String info) {
        List<Classinfo>list = classinfoMapper.selectInfo(info);
        return list;
    }


}
