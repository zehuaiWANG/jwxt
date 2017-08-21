package com.jwxt.service;

import com.jwxt.common.ServerResponse;
import com.jwxt.vo.NoticeVo;

import java.util.List;

public interface INoticeService {
    ServerResponse<String>insert(String contact,String author);
    ServerResponse<String> delet(Integer noticeId);
    ServerResponse<String> updateContact(String contact,String author,Integer id);
    List<NoticeVo> listAllNotice();
}
