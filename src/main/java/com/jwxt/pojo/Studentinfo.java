package com.jwxt.pojo;

import java.util.Date;

public class Studentinfo {
    private Integer studentId;

    private String classId;

    private String className;

    private String classTime;

    private Date createTime;

    private Date updateTime;

    public Studentinfo(Integer studentId, String classId, String className, String classTime, Date createTime, Date updateTime) {
        this.studentId = studentId;
        this.classId = classId;
        this.className = className;
        this.classTime = classTime;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime == null ? null : classTime.trim();
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
}