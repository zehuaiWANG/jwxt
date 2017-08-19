package com.jwxt.vo;

public class LoginfoVo {
    private Integer studentId;

    private String classId;

    private Integer operate;

    private String updateTime;

    public LoginfoVo() {
    }

    public LoginfoVo(Integer studentId, String classId, Integer operate, String updateTime) {
        this.studentId = studentId;
        this.classId = classId;
        this.operate = operate;
        this.updateTime = updateTime;
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
        this.classId = classId;
    }

    public Integer getOperate() {
        return operate;
    }

    public void setOperate(Integer operate) {
        this.operate = operate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
