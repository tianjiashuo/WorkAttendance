package com.example.demo.Repository.entity;

/*系统用户*/

public class User {

    private int id;
    private String empNo;
    private String empName;
    private String password;
    private String power;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmpNo(){ return empNo; }
    public void  setEmpNo(String empNo){ this.empNo = empNo; }
    public String getEmpName(){ return empName; }
    public void  setEmpName(String empName){ this.empName = empName; }
    public String getPassword(){ return password; }
    public void  setPassword(String password){ this.password = password; }
    public String getPower(){ return power; }
    public void  setPower(String power){ this.power = power; }

}
