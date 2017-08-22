package com.jwxt.controller.protal;

import com.jwxt.common.ResponseCode;
import com.jwxt.common.ServerResponse;
import com.jwxt.dao.NoticeStuMapper;
import com.jwxt.pojo.Notice;
import com.jwxt.service.INoticeService;
import com.jwxt.service.INoticeStuinfoService;
import com.jwxt.vo.NoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/noticeStu/")
public class NoticeStuController {
    @Autowired
    private INoticeStuinfoService iNoticeStuinfoService;
    @Autowired
    private INoticeService iNoticeService;
    @RequestMapping(value="noticeNum.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> noticeNum(HttpSession session){
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        String username = session.getAttribute("username").toString();
        Integer studentid = Integer.parseInt(username);
        return ServerResponse.createBySuccess(String.valueOf(iNoticeStuinfoService.getNoReadNotice(studentid)));
    }

    @RequestMapping(value="insertNotice.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> insertNotice(HttpSession session,Integer noticeId){
        if (session.getAttribute("username") == null){
            return ServerResponse.createErrorCodeMessage(ResponseCode.NEED_LOOGIN.getCode(),"用户未登录,请登录管理员");
        }
        String username = session.getAttribute("username").toString();
        Integer studentid = Integer.parseInt(username);
        return iNoticeStuinfoService.insertNoticeStu(studentid,noticeId);
    }

    @RequestMapping(value = "readNotice.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> readall(HttpSession session){
        List<NoticeVo>list = iNoticeService.listAllNotice();
        for (int i=0;i<list.size();i++)
            insertNotice(session,list.get(i).getNoticeId());
        return ServerResponse.createBySuccess("已读");
    }
}
