package com.workattendance.Service;


import com.workattendance.Repository.entity.User;

public class UserBo {
    private int id;
    private String empNo;
    private String empName;
    private int power;
    private static UserBo userbo = null;
    private UserBo(){

    };
    public UserBo(User u){
        this.power = u.getPower();
    }

    public static synchronized UserBo getUserBo(){
        if(userbo==null){
            userbo = new UserBo();
            return userbo;
        }else{
            return userbo;
        }
    }
    public static UserBo getUserBo(User u){
        userbo = new UserBo(u);
        return userbo;
    }

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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
}
