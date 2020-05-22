package com.workattendance.Repository.entity;

/*系统用户*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//mao加了user表里面有的但是类里面没有的属性
public class User {

    private int id;
    private String emp_no;
    private String emp_name;
    private String password;
    private String power;
    private String stete;
    private String years;

    public static List<String> onlyName(List<User> u){
        if(u==null||u.size()==0){
            return null;
        }
        ArrayList<String> usersName = new ArrayList<>();
        Iterator<User> it = u.iterator();
        while(it.hasNext()){
            usersName.add(it.next().getEmpName());
        }
        return usersName;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getEmpNo(){ return emp_no; }
    public void  setEmpNo(String empNo){ this.emp_no = empNo; }
    public String getEmpName(){ return emp_name; }
    public void  setEmpName(String empName){ this.emp_name = empName; }
    public String getPassword(){ return password; }
    public void  setPassword(String password){ this.password = password; }
    public String getPower(){ return power; }
    public void  setPower(String power){ this.power = power; }
    public String getStete() {
        return stete;
    }

    public void setStete(String stete) {
        this.stete = stete;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

}
