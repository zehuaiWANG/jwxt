package com.jwxt.controller.protal;

import com.jwxt.common.ResponseCode;
import com.jwxt.common.ServerResponse;
import com.jwxt.pojo.Notice;
import com.jwxt.service.INoticeService;
import com.jwxt.vo.NoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/notice/")
public class NoticeController {
    @Autowired
    private INoticeService iNoticeService;

    @Autowired
    private StudentinfoController studentinfoController;

    @RequestMapping(value="insert.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> insert(HttpSession session,String contact, String author){
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if (studentinfoController.isAdmin(session).getData().equals("1"))
            return iNoticeService.insert(contact,author);
        return ServerResponse.createByErrorMessage("请登录管理员");
    }

    @RequestMapping(value="delet.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> delet(Integer noticeId){
        return iNoticeService.delet(noticeId);
    }

    @RequestMapping(value="updateContact.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> updateContact(HttpSession session,String contact,String author,Integer id){
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if (studentinfoController.isAdmin(session).getData().equals("1"))
            return iNoticeService.updateContact(contact,author,id);
        return ServerResponse.createByErrorMessage("请登录管理员");
    }

    @RequestMapping(value="listAll.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<List<NoticeVo>> listAllNotice(HttpSession session){
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        if (studentinfoController.isAdmin(session).getData().equals("1"))
             return ServerResponse.createBySuccess(iNoticeService.listAllNotice());
        return ServerResponse.createByErrorMessage("请登录管理员");
    }
}
