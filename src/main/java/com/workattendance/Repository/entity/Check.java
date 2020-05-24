package com.workattendance.Repository.entity;

/*考勤表*/

public class Check{

    private int id;
    private String empNo;
    private String empName;
    private int leaveDays;
    private int absenteeismDays;
    private int lateDays;
    private int leaveEarlyDays;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(int days) {
        this.leaveDays = days;
    }

    public int getAbsenteeismDays() {
        return absenteeismDays;
    }

    public void setAbsenteeismDays(int absenteeismDays) {
        this.absenteeismDays = absenteeismDays;
    }

    public int getLateDays() {
        return lateDays;
    }

    public void setLateDays(int lateDays) {
        this.lateDays = lateDays;
    }

    public int getLeaveEarlyDays() {
        return leaveEarlyDays;
    }
    public void setLeaveEarlyDays(int leave_early_days) {
        this.leaveEarlyDays = leave_early_days;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
