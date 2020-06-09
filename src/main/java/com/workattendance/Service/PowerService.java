package com.workattendance.Service;

import com.workattendance.Repository.dao.PowerDao;
import com.workattendance.Repository.entity.Power;
import org.springframework.beans.factory.annotation.Autowired;

public class PowerService {
    @Autowired
    private PowerDao powerDao;
    @Autowired
    private UserBo userBo;

    //得到等级权限说明
    public Power getPower(String power){
       return powerDao.queryPowerByEmpPower(power);
    }
    //view_own_attendance权限判定
    public boolean getViewOwnAttendancePower(int powerId){
        return powerDao.queryViewOwnAttendancePowerById(powerId);
    }
    //view_all_attendance权限判定
    public boolean getViewAllAttendancePower(int powerId){
        return powerDao.queryViewAllAttendancePowerById(powerId);
    }
    //view_check
    public boolean getViewCheckPower(int powerId){
        return powerDao.queryViewCheckPowerById(powerId);
    }
    //view_all_leave
    public boolean getViewAllLeavePower(int powerId){
        return powerDao.queryViewAllLeavePowerById(powerId);
    }
    //view_pass_leave
    public boolean getViewPassLeavePower(int powerId){
        return powerDao.queryViewPassLeavePowerById(powerId);
    }
    //view_all_goout
    public boolean getViewAllGooutPower(int powerId){
        return powerDao.queryViewAllGooutPowerById(powerId);
    }
    //view_pass_goout
    public boolean getViewPassGooutPower(int powerId){
        return powerDao.queryViewPassGooutPowerById(powerId);
    }
    //set_annual_goout
    public boolean getSetAnnualGooutPower(int powerId){
        return powerDao.querySetAnnualGooutPowerById(powerId);
    }
    //set_leave_types
    public boolean getSetLeaveTypesPower(int powerId){
        return powerDao.querySetLeaveTypesPowerById(powerId);
    }
    //leave_approval
    public boolean getLeaveApprovalPower(int powerId){
        return powerDao.queryLeaveApprovalPowerById(powerId);
    }
    //goout_approval
    public boolean getGooutApprovalPower(int powerId){
        return powerDao.queryGooutApprovalPowerById(powerId);
    }
}
