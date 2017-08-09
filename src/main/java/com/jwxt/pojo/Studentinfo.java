package com.jwxt.pojo;

import java.util.Date;

public class Studentinfo {
    private Integer studentId;

    private String classId;

    private Date createTime;

    private Date updateTime;

    private String classTime;

    public Studentinfo(Integer studentId, String classId, Date createTime, Date updateTime, String classTime) {
        this.studentId = studentId;
        this.classId = classId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.classTime = classTime;
    }

    public Studentinfo() {
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime == null ? null : classTime.trim();
    }
}