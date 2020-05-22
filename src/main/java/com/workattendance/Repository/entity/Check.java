package com.workattendance.Repository.entity;

/*考勤表*/

public class Check{

    private int id;
    private String empNo;
    private String empName;
    private int days;
    private int absenteesim;
    private int late_days;
    private int leave_early_days;


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

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getAbsenteesim() {
        return absenteesim;
    }

    public void setAbsenteesim(int absenteesim) {
        this.absenteesim = absenteesim;
    }

    public int getLate_days() {
        return late_days;
    }

    public void setLate_days(int late_days) {
        this.late_days = late_days;
    }

    public int getLeave_early_days() {
        return leave_early_days;
    }
    public void setLeave_early_days(int leave_early_days) {
        this.leave_early_days = leave_early_days;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
