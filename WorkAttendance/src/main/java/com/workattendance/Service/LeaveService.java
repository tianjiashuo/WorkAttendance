package com.workattendance.Service;

import com.workattendance.Repository.dao.LeaveDao;
import com.workattendance.Repository.dao.PowerDao;
import com.workattendance.Repository.dao.UserDao;
import com.workattendance.Repository.entity.Leave;
import com.workattendance.Repository.entity.Leave_balances;
import com.workattendance.Repository.entity.Leave_types;
import com.workattendance.Repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service("leaveService")
public class LeaveService {

    @Autowired
    private LeaveDao leaveDao;
    private UserBo userBo = UserBo.getUserBo();
    @Autowired
    private PowerDao powerDao;

    /***
     * 请假申请
     * @author bai
     * @return
     */
    public Leave inserLeave(Leave leave){
        return leaveDao.insert(leave);
    }

    /***
     *员工修改申请
     * @author bai
     * @return
     */
    public Leave updateLeaveById(int id, Leave leave){
        return leaveDao.updateLeaveByid(id,leave);
    }

    /***
     * 删除申请
     * @author bai
     * @return
     */
    public void deleteLeaveById(int id){
        leaveDao.deleteLeaveById(id);
    }

    /***
     * 查询自己的请假记录
     * @author bai
     * @return
     */
    public List<Leave> queryLeaveByEmpNo (int emp_no){
        return leaveDao.queryLeaveByEmpNo(emp_no);
    }

    /***
     * 查询一条请假记录详情
     * @author bai
     * @return
     */
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


    /***
     * 项目经理审批外出申请
     * @author shuo
     * @return
     */
    public void auditByDivision(int id , Leave response){
        if(userBo.getPower()!=0){
            if(powerDao.queryLeaveApprovalPowerById(userBo.getPower())) {
                if (response.getState()) {
                    leaveDao.updateLeaveDivisionPass(id);
                } else {
                    leaveDao.updateLeaveRefuse(id);
                }
            }else{
                System.out.println("您没有审核请假权限");
                }
        }
        else{
            System.out.println("请先登陆");
        }
    }
    /***
     * 副经理审批外出申请
     * @author shuo
     * @return
     */
    public void auditByVice(int id ,Leave response){
        if(userBo.getPower()!=0){
            if(powerDao.queryLeaveApprovalPowerById(userBo.getPower())){
                Leave leave = leaveDao.queryLeaveById(id);
                Boolean isSalary = leaveDao.queryLeaveSalary(leave.getType());
                if(response.getState()){
                    Long days = SecondtoDay(convertTimeToLong(leave.getEnd_time())-convertTimeToLong(leave.getStart_time()));
                    if(days<daytoSecond(3)){
                        leaveDao.updateLeaveVicePass(id);
                        leaveDao.updateLeavePass(id);
                        if(isSalary){
                            Long balances = leaveDao.queryLeaveBalance(leave.getEmp_no(),leave.getType())-days;
                            leaveDao.updateLeaveBalance(leave.getEmp_no(),leave.getType(),balances);
                        }
                    }
                    else{
                        leaveDao.updateLeaveVicePass(id);
                    }
                    }
                    else{
                    leaveDao.updateLeaveRefuse(id);
                    }
                }
                else{
                    System.out.println("您没有审核请假权限");
                }
        }
        else{
            System.out.println("请先登陆");
        }
    }

    /***
     * 总经理审批请假申请
     * @author shuo
     * @return
     */
    public void auditByManager(int id ,Leave response){
        if(userBo.getPower()!=0){
            if(powerDao.queryLeaveApprovalPowerById(userBo.getPower())){
                Leave leave = leaveDao.queryLeaveById(id);
                Boolean isSalary = leaveDao.queryLeaveSalary(leave.getType());
                if(response.getState()){
                    leaveDao.updateLeaveManagerPass(id);
                    leaveDao.updateLeavePass(id);
                    if(isSalary){
                        Long days =  SecondtoDay(convertTimeToLong(leave.getEnd_time())-convertTimeToLong(leave.getStart_time()));
                        Long balances = leaveDao.queryLeaveBalance(leave.getEmp_no(),leave.getType())-days;
                        leaveDao.updateLeaveBalance(leave.getEmp_no(),leave.getType(),balances);
                    }
                }
                else{
                    leaveDao.updateLeaveRefuse(id);
                }
            }
            else{
                System.out.println("您没有审核请假权限");
            }
        }
        else{
            System.out.println("请先登陆");
        }

    }


    /***
     * datetime转时间戳
     * @author shuo
     * @return
     */
    public static Long convertTimeToLong(String time) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(time, ftf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()/1000;
    }


    /***
     * 天转秒
     * @author shuo
     * @return
     */
    public static Long daytoSecond(int days){
        long oneDaySeconds = 86400;
        return days*oneDaySeconds;
    }

    /***
     * 秒转天 四舍五入
     * @author shuo
     * @return
     */
    public static Long SecondtoDay(Long seconds){
        long oneDaySeconds = 86400;
        Long days =  seconds/oneDaySeconds;
        if(seconds%oneDaySeconds < oneDaySeconds/2){
            return days;
        }
        else{
            return days+1;
        }
    }

    //根据工龄查员工编号
    public List<User> queryEmpNoByYears(int lowYear, int highYear){
        return leaveDao.queryEmpNoByYears(lowYear,highYear);
    }

    //查询假期编号
    public List<Leave_types> queryLeaveType(int id){

    return leaveDao.queryLeaveType(id);

    }

    //更改可休假期
    public void setLeaveBalances(Leave_balances lb){

        //leaveDao.setLeaveBalances(lb);
        if(userBo.getPower()!=0){
            if(powerDao.querySetLeaveTypesPowerById(userBo.getPower())){
                leaveDao.setLeaveBalances(lb);
            }
            else{
                System.out.println("您没有审核请假权限");
            }
        }
        else {
            System.out.println("请先登陆");
        }
    }

    //设置年假
    public void setYearLeave(int emp_no,int days){
        //leaveDao.updateExistLeaveBalances(emp_no,days);
        if(userBo.getPower()!=0){
            if(powerDao.querySetLeaveTypesPowerById(userBo.getPower())){
                leaveDao.updateExistLeaveBalances(emp_no,days);
            }
            else{
                System.out.println("您没有审核请假权限");
            }
        }
        else {
            System.out.println("请先登陆");
        }
    }


}
