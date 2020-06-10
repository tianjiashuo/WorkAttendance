package com.workattendance.Repository.entity;

public class Leave_types {
    private int id;
    private String leaveName;
    private int days;

    public int gettId(){return id;}
    public void setId(int id){this.id = id;}
    public String getLeaveName(){return leaveName;}
    public void setLeaveName(String leaveName){this.leaveName = leaveName;}
    public int getDays(){return days;}
    public void setDays(int days){this.days = days;}
}
