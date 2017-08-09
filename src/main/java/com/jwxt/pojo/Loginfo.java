package com.jwxt.pojo;

import java.util.Date;

public class Loginfo {
    private Integer studentId;

    private String classId;

    private Integer operate;

    private String ip;

    private Date updateTime;

    public Loginfo(Integer studentId, String classId, Integer operate, String ip, Date updateTime) {
        this.studentId = studentId;
        this.classId = classId;
        this.operate = operate;
        this.ip = ip;
        this.updateTime = updateTime;
    }

    public Loginfo() {
        super();
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public Integer getOperate() {
        return operate;
    }

    public void setOperate(Integer operate) {
        this.operate = operate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}