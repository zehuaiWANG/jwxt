package com.jwxt.controller.protal;

import com.jwxt.common.ServerResponse;
import com.jwxt.dao.NoticeStuMapper;
import com.jwxt.service.INoticeStuinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/noticeStu/")
public class NoticeStuController {
    @Autowired
    private INoticeStuinfoService iNoticeStuinfoService;
    @RequestMapping(value="noticeNum.do",method = RequestMethod.POST)
    @ResponseBody
    public int noticeNum(Integer studentid){
        return iNoticeStuinfoService.getNoReadNotice(studentid);
    }
    @RequestMapping(value="insertNotice.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> insertNotice(Integer studentid,Integer noticeId){
        return iNoticeStuinfoService.insertNoticeStu(studentid,noticeId);
    }
}
