package com.jwxt.dao;

import com.jwxt.pojo.NoticeStu;

public interface NoticeStuMapper {
    int insert(NoticeStu record);

    int insertSelective(NoticeStu record);

    int findNoticeInfo(Integer studentid);

    int delet(Integer studentid);
}