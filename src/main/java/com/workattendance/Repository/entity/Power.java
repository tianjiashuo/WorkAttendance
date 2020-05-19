package com.workattendance.Repository.entity;

/*系统权限*/

public class Power {

    private int id;
    private String power;
    private boolean viewLeave;
    private boolean viewAttendance;
    private boolean viewGoOut;
    private boolean annualLeavePower;
    private boolean leavePower;
    private boolean goOutPower;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPower(){ return power; }
    public void  setPower(String power){ this.power = power; }
    public boolean getViewLeave(){ return viewLeave; }
    public void  setViewLeave(boolean viewLeave){ this.viewLeave = viewLeave; }
    public boolean getViewAttendance(){ return viewAttendance; }
    public void  setViewAttendance(boolean viewAttendance){ this.viewAttendance= viewAttendance; }
    public boolean getViewGoOut(){ return viewGoOut; }
    public void  setViewGoOut(boolean viewGoOut){ this.viewGoOut = viewGoOut; }
    public boolean getAnnualLeavePower(){ return annualLeavePower; }
    public void  setAnnualLeavePower(boolean annualLeavePower){ this.annualLeavePower = annualLeavePower; }
    public boolean getLeavePower(){ return leavePower; }
    public void  setLeavePower(boolean leavePower){ this.leavePower = leavePower; }
    public boolean getGoOutPower(){ return goOutPower; }
    public void  setGoOutPower(boolean goOutPower){ this.goOutPower= goOutPower; }

}
