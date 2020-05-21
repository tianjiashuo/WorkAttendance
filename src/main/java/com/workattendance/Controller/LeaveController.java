package com.workattendance.Controller;

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

    //查询自己的请假记录ok
    @GetMapping("/leave/{empNo}")
    List<Leave> getLeaveByEmpNo(@PathVariable int empNo) {
        return leaveService.queryLeaveByEmpNo(empNo);
    }

    //修改申请ok
    @PutMapping("/leave/{id}")
    public Leave updateLeaveById(@PathVariable int id,@RequestBody Leave leave){
        return leaveService.updateLeaveById(id,leave);
    }

    //删除申请ok
    @DeleteMapping("/leave/{id}")
    public void deleteLeaveById(@PathVariable int id){
        leaveService.deleteLeaveById(id);
    }

//    //查询所有人的请假记录
//    @GetMapping("/leave/{empNo}")
//    List<Leave>  getLeave(String empNo) {
//        String userPower = userService.getUserPower(empNo);
//        Power power = powerService.getPower(userPower);
//        //有权限才可以查看
//        if(power.getViewLeave()){
//            return leaveService.queryLeave();
//        }else{
//            return null;
//        }
//    }

//    //审批申请
//    @PutMapping("/leave/{loginNo,empNo,id,leave}")
//    Leave examineById(String loginNo, String empNo, int id,Leave leave){
//        String userPower = userService.getUserPower(loginNo);
//        Power power = powerService.getPower(userPower);
//        //有权限才可以审批
//        if(power.getLeavePower()){
//            //批准事假则请假次数加1
//            if(leave.getState().equals("批准") && leave.getType().equals("事假")){
//                int leaveTime = checkService.getLeaveTime(empNo);
//                leaveTime += 1;
//                checkService.updateLeaveTime(empNo,leaveTime);
//            }
//            return leaveService.examineById(id,leave);
//        }else {
//            return null;
//        }
//    }
}
