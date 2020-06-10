package com.workattendance.Repository.entity;

public class Leave_balances {
    private int id;
    private int empNo;
    private int leaveId;
    private int days;
    private int balances;

    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    public int getEmpNo(){return empNo;}
    public void setEmpNo(int empNo){this.empNo = empNo;}
    public int getLeaveId(){return leaveId;}
    public void setLeaveId(int leaveId){this.leaveId = leaveId;}
    public int getDays(){return days;}
    public void setDays(int days){this.days = days;}
    public int getBalances(){return balances;}
    public void setBalances(int balances){this.balances = balances;}
}
