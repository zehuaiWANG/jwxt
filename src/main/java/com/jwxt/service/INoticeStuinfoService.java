package com.jwxt.service;

import com.jwxt.common.ServerResponse;

public interface INoticeStuinfoService {
    int getNoReadNotice(Integer studentid);
    ServerResponse<String> insertNoticeStu(Integer studentid, Integer noticeid);
    ServerResponse<String> delNoticeStu(Integer studentid);
}
