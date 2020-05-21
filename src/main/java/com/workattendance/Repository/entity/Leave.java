package com.workattendance.Repository.entity;

/*请假记录*/

public class Leave {

    private int id;
    private int emp_no;
    private int type;
    private String start_time;
    private String end_time;
    private String reason;
    private boolean state;
    private boolean division_manager_state;
    private boolean vice_manager_state;
    private boolean manager_state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public int getType(){return type;}

    public void setType(int type) {
        this.type = type;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getDivision_manager_state(){return division_manager_state;}

    public void setDivision_manager_state(boolean division_manager_state){
        this.division_manager_state = division_manager_state;
    }

    public boolean getVice_manager_state(){return vice_manager_state;}

    public void setVice_manager_state(boolean vice_manager_state){
        this.vice_manager_state = vice_manager_state;
    }

    public boolean getManager_state(){return manager_state;}

    public void setManager_state(boolean manager_state){
        this.manager_state = manager_state;
    }
}
