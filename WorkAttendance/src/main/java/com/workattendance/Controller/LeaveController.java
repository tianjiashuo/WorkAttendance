package com.workattendance.Controller;

import com.workattendance.Repository.entity.*;
import com.workattendance.Service.CheckService;
import com.workattendance.Service.LeaveService;
import com.workattendance.Service.PowerService;
import com.workattendance.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LeaveController {

    @Autowired
    private LeaveService leaveService;
    private PowerService powerService;
    private UserService userService;
    private CheckService checkService;

    /***
     * 请假申请
     * @author bai
     * @return
     */
    @PostMapping("/leave")
    Leave inserLeave(@RequestBody Leave leave) {
        return leaveService.inserLeave(leave);
    }

    /***
     * 查询自己所有的请假记录
     * @author bai
     * @return
     */
    @GetMapping("/leave/{empNo}")
    List<Leave> getLeaveByEmpNo(@PathVariable int empNo) {
        return leaveService.queryLeaveByEmpNo(empNo);
    }

    /***
     * 根据id查询请假记录详情
     * @author bai
     * @return
     */
    @GetMapping("/leaveDetail/{id}")
    Leave getLeaveById(@PathVariable int id) {
        return leaveService.queryLeaveById(id);
    }

    /***
     * 修改申请
     * @author bai
     * @return
     */
    @PutMapping("/leave/{id}")
    public Leave updateLeaveById(@PathVariable int id,@RequestBody Leave leave){
        return leaveService.updateLeaveById(id,leave);
    }

    /***
     * 删除申请
     * @author bai
     * @return
     */
    @DeleteMapping("/leave/{id}")
    public void deleteLeaveById(@PathVariable int id){
        leaveService.deleteLeaveById(id);
    }


    /***
     * 查询所有人的请假记录
     * @author mao
     * @param fromDate
     * @param endDate
     * @return
     */
    @GetMapping("/leave/{fromDate}/{endDate}")
    List<Leave>  getLeave(@PathVariable String fromDate, @PathVariable  String endDate) {
        return leaveService.queryAllLeave(fromDate, endDate);
   }

    /***
     * 项目经理审批请假申请
     * @author shuo
     * @return
     */
    @PutMapping("/leaveAuditDivision/{id}")
    public void auditByDivision(@PathVariable int id,@RequestBody Leave response){
        leaveService.auditByDivision(id,response);
    }

    /***
     * 副经理审批请假申请
     * @author shuo
     * @return
     */
    @PutMapping("/leaveAuditVice/{id}")
    public void auditByVice(@PathVariable int id,@RequestBody Leave response){
        leaveService.auditByVice(id,response);
    }


    /***
     * 总经理审批请假申请
     * @author shuo
     * @return
     */
    @PutMapping("/leaveAuditManager/{id}")
    public void auditByManager(@PathVariable int id,@RequestBody Leave response){
        leaveService.auditByManager(id,response);
    }

    //查询假期id
    @GetMapping("/queryLeaveTypes/{id}")
    List<Leave_types> queryLeaveTypes(@PathVariable int id){return leaveService.queryLeaveType(id);}

    //根据工龄查询员工
    @GetMapping("/queryEmpNoByYears/{lowyear}/{highyear}")
    List<User> queryEmpNoByYears(@PathVariable int lowyear, @PathVariable int highyear){
        return leaveService.queryEmpNoByYears(lowyear,highyear);
    }

    //设置假期
    @PutMapping("/seLleave_balances")
    public void setLeaveBalances(@RequestBody Leave_balances lb){
        leaveService.setLeaveBalances(lb);
    }

    //设置年假
    @PutMapping("setYearLeave/{emp_no}/{days}")
    public void setYearLeave(@PathVariable int emp_no, @PathVariable int days){
        leaveService.setYearLeave(emp_no,days);
    }
}
