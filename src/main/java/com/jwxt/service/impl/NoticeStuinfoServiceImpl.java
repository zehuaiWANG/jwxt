package com.jwxt.service.impl;

import com.jwxt.common.ServerResponse;
import com.jwxt.dao.NoticeMapper;
import com.jwxt.dao.NoticeStuMapper;
import com.jwxt.pojo.NoticeStu;
import com.jwxt.service.INoticeStuinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iNoticeStuinfoService")
public class NoticeStuinfoServiceImpl implements INoticeStuinfoService {

    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private NoticeStuMapper noticeStuMapper;

    @Override
    public int getNoReadNotice(Integer studentid){
        return noticeMapper.findAllNotice().size()-noticeStuMapper.findNoticeInfo(studentid);
    }

    @Override
    public ServerResponse<String> insertNoticeStu(Integer studentid,Integer noticeid){
        NoticeStu noticeStu = new NoticeStu();
        noticeStu.setStudentid(studentid);
        noticeStu.setNoticeid(noticeid);
        int count =  noticeStuMapper.insert(noticeStu);
        if (count > 0)
            return ServerResponse.createBySuccess("增加成功");
        return ServerResponse.createByErrorMessage("增加失败");
    }
}
