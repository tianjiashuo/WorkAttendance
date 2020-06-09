package com.workattendance.Repository.entity;

/*系统权限*/
public class Power {

    private int id;
    private int positionId;
    private boolean view_own_attendance;
    private boolean view_all_attendance;
    private boolean view_check;
    private boolean view_all_leave;
    private boolean view_pass_leave;
    private boolean view_all_goout;
    private boolean view_pass_goout;
    private boolean set_annual_leave;
    private boolean set_leave_types;
    private boolean leave_approval;
    private boolean goout_approval;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public boolean isView_own_attendance() {
        return view_own_attendance;
    }

    public void setView_own_attendance(boolean view_own_attendance) {
        this.view_own_attendance = view_own_attendance;
    }

    public boolean isView_all_attendance() {
        return view_all_attendance;
    }

    public void setView_all_attendance(boolean view_all_attendance) {
        this.view_all_attendance = view_all_attendance;
    }

    public boolean isView_check() {
        return view_check;
    }

    public void setView_check(boolean view_check) {
        this.view_check = view_check;
    }

    public boolean isView_all_leave() {
        return view_all_leave;
    }

    public void setView_all_leave(boolean view_all_leave) {
        this.view_all_leave = view_all_leave;
    }

    public boolean isView_pass_leave() {
        return view_pass_leave;
    }

    public void setView_pass_leave(boolean view_pass_leave) {
        this.view_pass_leave = view_pass_leave;
    }

    public boolean isView_all_goout() {
        return view_all_goout;
    }

    public void setView_all_goout(boolean view_all_goout) {
        this.view_all_goout = view_all_goout;
    }

    public boolean isView_pass_goout() {
        return view_pass_goout;
    }

    public void setView_pass_goout(boolean view_pass_goout) {
        this.view_pass_goout = view_pass_goout;
    }

    public boolean isSet_annual_leave() {
        return set_annual_leave;
    }

    public void setSet_annual_leave(boolean set_annual_leave) {
        this.set_annual_leave = set_annual_leave;
    }

    public boolean isSet_leave_types() {
        return set_leave_types;
    }

    public void setSet_leave_types(boolean set_leave_types) {
        this.set_leave_types = set_leave_types;
    }

    public boolean isLeave_approval() {
        return leave_approval;
    }

    public void setLeave_approval(boolean leave_approval) {
        this.leave_approval = leave_approval;
    }

    public boolean isGoout_approval() {
        return goout_approval;
    }

    public void setGoout_approval(boolean goout_approval) {
        this.goout_approval = goout_approval;
    }








}
