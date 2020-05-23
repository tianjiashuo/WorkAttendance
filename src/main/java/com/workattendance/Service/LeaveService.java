package com.workattendance.Service;

import com.workattendance.Repository.dao.LeaveDao;
import com.workattendance.Repository.entity.GoOut;
import com.workattendance.Repository.entity.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

    /*** shuo***/
    //项目经理审批外出申请
    public void auditByDivision(int id , Leave response){
        if(response.getState()){
            leaveDao.updateLeaveDivisionPass(id);
        }
        else{
            leaveDao.updateLeaveRefuse(id);
        }
    }

    //副经理审批外出申请
    public void auditByVice(int id ,Leave response){
        Leave leave = leaveDao.queryLeaveById(id);
        if(response.getState()){
            if(convertTimeToLong(leave.getEnd_time())-convertTimeToLong(leave.getStart_time())>daytoSecond(3)){
                leaveDao.updateLeaveVicePass(id);
                leaveDao.updateLeavePass(id);
            }
            else{
                leaveDao.updateLeaveVicePass(id);
            }
        }
        else{
            leaveDao.updateLeaveRefuse(id);
        }
    }

    //总经理审批外出申请
    public void auditByManager(int id ,Leave response){
        if(response.getState()){
            leaveDao.updateLeaveManagerPass(id);
            leaveDao.updateLeavePass(id);
        }
        else{
            leaveDao.updateLeaveRefuse(id);
        }
    }

    public static Long convertTimeToLong(String time) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(time, ftf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()/1000;
    }

    public static Long daytoSecond(int days){
        long oneDaySeconds = 86400;
        return days*oneDaySeconds;
    }


}
