package com.jwxt.controller.protal;

import com.jwxt.pojo.Loginfo;
import com.jwxt.service.ILoginfoService;
import com.jwxt.util.DateTimeUtil;
import com.jwxt.vo.LoginfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/log/")
public class LoginfoController {

    @Autowired
    private ILoginfoService iLoginfoService;
    @RequestMapping(value ="getallLoginfo.do",method = RequestMethod.POST)
    @ResponseBody
    public List<LoginfoVo> getallLoginfo(Integer studentid){
        List<Loginfo> list = iLoginfoService.findLoginfoList(studentid);
        List<LoginfoVo> list2 = new ArrayList<LoginfoVo>();
        LoginfoVo loginfoVo = new LoginfoVo();
        for (Loginfo loginfoItem : list){
            loginfoVo.setClassId(loginfoItem.getClassId());
            loginfoVo.setOperate(loginfoItem.getOperate());
            loginfoVo.setStudentId(loginfoItem.getStudentId());
            loginfoVo.setUpdateTime(DateTimeUtil.dateToStr(loginfoItem.getUpdateTime()));
            list2.add(loginfoVo);
        }
        return list2;
    }
}
