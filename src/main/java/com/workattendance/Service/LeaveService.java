package com.workattendance.Service;

import com.workattendance.Repository.dao.LeaveDao;
import com.workattendance.Repository.entity.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("leaveService")
public class LeaveService {

    @Autowired
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
    public List<Leave> queryLeaveByEmpNo (int emp_no){
        return leaveDao.queryLeaveByEmpNo(emp_no);
    }

    //查询一条请假记录详情
    public Leave queryLeaveById (int id){
        return leaveDao.queryLeaveById(id);
    }

    /***
     * 查询所有人的请假信息
     * @return
     */
    public List<Leave> queryAllLeave(String fromDate,String endDate){
        return leaveDao.queryAllLeave(fromDate,endDate);
    }

//    //审批申请
//    public Leave examineById(int id ,Leave leave){
//        return leaveDao.updateLeaveByPower(id ,leave);
//    }
}
