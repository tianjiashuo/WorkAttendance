package com.workattendence.Service;

import com.workattendence.Repository.dao.CheckDao;
import com.workattendence.Repository.entity.Check;

public class CheckService {

    private CheckDao checkDao;

    //更新年假
    public Check updateAnnualLeave(String empNo, int annualLeave){
        return checkDao.updateAnnualLeaveByEmpNo(empNo,annualLeave);
    }

    public int getAnnualLeave(String empNo){
        int annualLeave = checkDao.queryCheckByEmpNo(empNo).getAnnualLeave();
        return annualLeave;
    }

    //更新请假次数
    public Check updateLeaveTime(String empNo, int leaveTime){
        return checkDao.updateLeaveTimeByEmpNo(empNo,leaveTime);
    }

    public int getLeaveTime(String empNo){
        int leaveTime = checkDao.queryCheckByEmpNo(empNo).getLeaveTime();
        return leaveTime;
    }

    //更新缺勤次数
    public Check updateAbsenteeism(String empNo, int absenteeism){
        return checkDao.updateAbsenteeismByEmpNo(empNo,absenteeism);
    }

    public int getAbsenteeism(String empNo){
        int absenteeism = checkDao.queryCheckByEmpNo(empNo).getAbsenteeism();
        return absenteeism;
    }



}
