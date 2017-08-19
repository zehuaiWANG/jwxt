package com.jwxt.service.impl;

import com.jwxt.dao.LoginfoMapper;
import com.jwxt.pojo.Loginfo;
import com.jwxt.service.ILoginfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iLoginfoService")
public class LoginfoServiceImpl implements ILoginfoService{

    @Autowired
    private LoginfoMapper loginfoMapper;
    @Override
    public List<Loginfo> findLoginfoList(Integer studentid) {
        List<Loginfo>list = loginfoMapper.selectAllLogInfo(studentid);
        return list;
    }
}
