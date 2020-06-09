package com.workattendance.Controller;


public class UserVo {
    private int id;
    private String empNo;
    private String empName;
    private String power;
    private UserVo userVo;

    private UserVo(int id,String empNo,String empName,String power){
        userVo.id = id;
        userVo.empNo = empNo;
        userVo.empName = empName;
        userVo.power = power;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpNo(){ return empNo; }
    public void  setEmpNo(String empNo){ this.empNo = empNo; }
    public String getEmpName(){ return empName;}
    public void  setEmpName(String empName){ this.empName = empName; }
    public String getPower(){ return power; }
    public void  setPower(String power){ this.power = power; }


}
