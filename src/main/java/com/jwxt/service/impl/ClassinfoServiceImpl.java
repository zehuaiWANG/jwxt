package com.jwxt.service.impl;



import com.jwxt.common.ServerResponse;
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

    @Override
    public boolean checkClassinfo(String classid, String classname,String classtime){
        int count = classinfoMapper.selectByClassidAndClassnameAndClasstime(classid,classname,classtime);
        if (count > 0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public ServerResponse<String>insert(Classinfo info){
        int count = classinfoMapper.insert(info);
        if (count > 0){
            return ServerResponse.createBySuccess("插入成功");
        }
        return ServerResponse.createByErrorMessage("插入失败");
    }

    @Override
    public  ServerResponse<String>delet(String classname,String classid){
        int count =classinfoMapper.delet(classid,classname);
        if(count > 0){
            return ServerResponse.createBySuccess("删除成功");
        }else{
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }

    @Override
    public boolean selectByIdAndName(String classid, String classname){
        int count = classinfoMapper.findByIdAndName(classid,classname);
        if (count > 0){
            return true;
        }else
            return false;
    }
}
