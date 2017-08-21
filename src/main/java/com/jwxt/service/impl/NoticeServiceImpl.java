package com.jwxt.service.impl;

import com.jwxt.common.ServerResponse;
import com.jwxt.dao.NoticeMapper;
import com.jwxt.pojo.Notice;
import com.jwxt.service.INoticeService;
import com.jwxt.util.DateTimeUtil;
import com.jwxt.vo.NoticeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("iNoticeService")
public class NoticeServiceImpl implements INoticeService{

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public ServerResponse<String> insert(String contact, String author) {
        Notice notice = new Notice();
        notice.setNoticeAuthor(author);
        notice.setNoticeContact(contact);
        notice.setUpdatetime(new Date());
        if (noticeMapper.insertSelective(notice)>0)
            return ServerResponse.createBySuccess("增加公告成功");
        return ServerResponse.createByErrorMessage("增加失败");
    }

    @Override
    public ServerResponse<String> delet(Integer noticeId){
        if (noticeMapper.deleteByPrimaryKey(noticeId)>0)
            return ServerResponse.createBySuccessMessage("删除成功");
        return ServerResponse.createByErrorMessage("删除失败");
    }

    @Override
    public ServerResponse<String> updateContact(String contact,String author,Integer id){
        Notice notice = new Notice();
        notice.setUpdatetime(new Date());
        notice.setNoticeContact(contact);
        notice.setNoticeId(id);
        notice.setNoticeAuthor(author);
        if (noticeMapper.updateByPrimaryKey(notice) >0 )
            return ServerResponse.createBySuccessMessage("修改成功");
        return ServerResponse.createByErrorMessage("修改失败");
    }

    @Override
    public List<NoticeVo> listAllNotice(){
        List<Notice> list = noticeMapper.findAllNotice();
        List<NoticeVo> list2 = new ArrayList<NoticeVo>();
        for (Notice notice : list){
            NoticeVo noticeVo = new NoticeVo();
            noticeVo.setNoticeAuthor(notice.getNoticeAuthor());
            noticeVo.setNoticeContact(notice.getNoticeContact());
            noticeVo.setNoticeId(notice.getNoticeId());
            noticeVo.setUpdatetime(DateTimeUtil.dateToStr(notice.getUpdatetime()));
            list2.add(noticeVo);
        }
        Collections.reverse(list2);
        return list2;
    }
}
