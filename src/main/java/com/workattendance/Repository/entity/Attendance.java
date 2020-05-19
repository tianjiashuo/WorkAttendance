package com.workattendance.Repository.entity;

/*出勤记录*/

public class Attendance {

    private int id;
    private String empNo;
    private String date;
    private String startTime;
    private String endTime;
    private String state;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmpNo(){ return empNo; }
    public void  setEmpNo(String empNo){ this.empNo = empNo; }
    public String getDate(){ return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
