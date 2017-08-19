package com.jwxt.service;

import com.jwxt.pojo.Loginfo;

import java.util.List;

public interface ILoginfoService {
    List<Loginfo> findLoginfoList(Integer studentid);
}
