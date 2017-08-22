package com.jwxt.pojo;

import java.util.Date;

public class Classinfo {
    private String classId;

    private String className;

    private Integer classCredit;

    private String classTutor;

    private String classTime;

    private String classLocation;

    private Integer classVacancies;

    private Date createTime;

    private Date updateTime;

    private Integer necessary;

    private Integer opento;

    public Classinfo(String classId, String className, Integer classCredit, String classTutor, String classTime, String classLocation, Integer classVacancies, Date createTime, Date updateTime, Integer necessary, Integer opento) {
        this.classId = classId;
        this.className = className;
        this.classCredit = classCredit;
        this.classTutor = classTutor;
        this.classTime = classTime;
        this.classLocation = classLocation;
        this.classVacancies = classVacancies;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.necessary = necessary;
        this.opento = opento;
    }

    public Classinfo() {
        super();
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

    public Integer getClassCredit() {
        return classCredit;
    }

    public void setClassCredit(Integer classCredit) {
        this.classCredit = classCredit;
    }

    public String getClassTutor() {
        return classTutor;
    }

    public void setClassTutor(String classTutor) {
        this.classTutor = classTutor == null ? null : classTutor.trim();
    }

    public String getClassTime() {
        return classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime == null ? null : classTime.trim();
    }

    public String getClassLocation() {
        return classLocation;
    }

    public void setClassLocation(String classLocation) {
        this.classLocation = classLocation == null ? null : classLocation.trim();
    }

    public Integer getClassVacancies() {
        return classVacancies;
    }

    public void setClassVacancies(Integer classVacancies) {
        this.classVacancies = classVacancies;
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

    public Integer getNecessary() {
        return necessary;
    }

    public void setNecessary(Integer necessary) {
        this.necessary = necessary;
    }

    public Integer getOpento() {
        return opento;
    }

    public void setOpento(Integer opento) {
        this.opento = opento;
    }
}