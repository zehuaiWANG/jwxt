package com.jwxt.pojo;

public class StudentinfoData {
    private Integer studentId;

    private String classId;

    private String className;

    private String week;

    private String day;

    private String time;

    private String location;

    private Integer class_time_num;

    public StudentinfoData(){}
    public StudentinfoData(Integer studentId, String classId, String className, String week, String day, String time, String location,Integer class_time_num) {
        this.studentId = studentId;
        this.classId = classId;
        this.className = className;
        this.week = week;
        this.day = day;
        this.time = time;
        this.location = location;
        this.class_time_num = class_time_num;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getClass_time_num() {
        return class_time_num;
    }

    public void setClass_time_num(Integer class_time_num) {
        this.class_time_num = class_time_num;
    }
}
