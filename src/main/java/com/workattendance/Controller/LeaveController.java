package com.workattendance.Controller;

import com.workattendance.Repository.entity.GoOut;
import com.workattendance.Repository.entity.Leave;
import com.workattendance.Repository.entity.Power;
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

    //请假申请ok
    @PostMapping("/leave")
    Leave inserLeave(@RequestBody Leave leave) {
        return leaveService.inserLeave(leave);
    }

    //查询自己所有的请假记录ok
    @GetMapping("/leave/{empNo}")
    List<Leave> getLeaveByEmpNo(@PathVariable int empNo) {
        return leaveService.queryLeaveByEmpNo(empNo);
    }

    //根据id查询请假记录详情ok
    @GetMapping("/leaveDetail/{id}")
    Leave getLeaveById(@PathVariable int id) {
        return leaveService.queryLeaveById(id);
    }

    //修改申请okid
    @PutMapping("/leave/{id}")
    public Leave updateLeaveById(@PathVariable int id,@RequestBody Leave leave){
        return leaveService.updateLeaveById(id,leave);
    }

    //删除申请ok
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

    //项目经理审批外出申请
    @PutMapping("/leaveAuditDivision/{id}")
    public void auditByDivision(@PathVariable int id,@RequestBody Leave response){
        leaveService.auditByDivision(id,response);
    }

    //副经理审批外出申请
    @PutMapping("/leaveAuditVice/{id}")
    public void auditByVice(@PathVariable int id,@RequestBody Leave response){
        leaveService.auditByVice(id,response);
    }

    //总经理审批外出申请
    @PutMapping("/leaveAuditManager/{id}")
    public void auditByManager(@PathVariable int id,@RequestBody Leave response){
        leaveService.auditByManager(id,response);
    }
}
