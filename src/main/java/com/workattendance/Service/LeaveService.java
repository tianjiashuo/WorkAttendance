package com.workattendance.Service;

import com.workattendance.Repository.dao.LeaveDao;
import com.workattendance.Repository.entity.Leave;

import java.util.List;

public class LeaveService {

    private LeaveDao leaveDao;

    //请假申请
    public Leave inserLeave(Leave leave){
        return leaveDao.insert(leave);
    }

    //员工修改申请
    public Leave updateLeaveById(int id, Leave leave){
        return leaveDao.updateLeaveByid(id,leave);
    }

    //删除申请
    public void deleteLeaveById(int id){
        leaveDao.deleteLeaveById(id);
    }

    //查询自己的请假记录
    public List<Leave> queryLeaveByEmpNo (String empNo){
        return leaveDao.queryLeaveByEmpNo(empNo);
    }

    //查询所有人的请假记录
    public List<Leave> queryLeave(){
        return leaveDao.queryLeave();
    }

    //审批申请
    public Leave examineById(int id ,Leave leave){
        return leaveDao.updateLeaveByPower(id ,leave);
    }
}
