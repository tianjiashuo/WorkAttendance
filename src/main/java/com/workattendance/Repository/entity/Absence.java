package com.workattendance.Repository.entity;

//暂时只用于获取的接口
/*** mao***/
public abstract class Absence {

    public abstract int getId();

    public abstract int getEmp_no();

    public abstract String getStart_time();


    public abstract String getEnd_time();

    public abstract String getReason();

    public abstract boolean getState();

    public abstract boolean getDivision_manager_state();

    public abstract boolean getVice_manager_state();

    public abstract boolean getManager_state();

    public int getType() throws Exception {
       throw new Exception("no type");
    }


}
