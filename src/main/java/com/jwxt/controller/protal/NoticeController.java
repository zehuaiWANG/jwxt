package com.jwxt.controller.protal;

import com.jwxt.common.ServerResponse;
import com.jwxt.pojo.Notice;
import com.jwxt.service.INoticeService;
import com.jwxt.vo.NoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/notice/")
public class NoticeController {
    @Autowired
    private INoticeService iNoticeService;


    @RequestMapping(value="insert.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> insert(String contact, String author){
        return iNoticeService.insert(contact,author);
    }

    @RequestMapping(value="delet.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delet(Integer noticeId){
        return iNoticeService.delet(noticeId);
    }

    @RequestMapping(value="updateContact.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> updateContact(String contact,String author,Integer id){
        return iNoticeService.updateContact(contact,author,id);
    }

    @RequestMapping(value="listAll.do",method = RequestMethod.POST)
    @ResponseBody
    public List<NoticeVo> listAllNotice(){
        return iNoticeService.listAllNotice();
    }
}
