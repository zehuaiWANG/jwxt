package com.jwxt.pojo;

public class NoticeStu {
    private Integer studentid;

    private Integer noticeid;

    public NoticeStu(Integer studentid, Integer noticeid) {
        this.studentid = studentid;
        this.noticeid = noticeid;
    }

    public NoticeStu() {
        super();
    }

    public Integer getStudentid() {
        return studentid;
    }

    public void setStudentid(Integer studentid) {
        this.studentid = studentid;
    }

    public Integer getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(Integer noticeid) {
        this.noticeid = noticeid;
    }
}