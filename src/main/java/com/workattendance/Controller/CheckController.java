package com.workattendance.Controller;

import com.workattendance.Repository.entity.Check;
import com.workattendance.Repository.entity.Power;
import com.workattendance.Service.CheckService;
import com.workattendance.Service.PowerService;
import com.workattendance.Service.UserService;
import org.springframework.web.bind.annotation.PutMapping;

public class CheckController {

    private CheckService checkService;
    private PowerService powerService;
    private UserService userService;

//    //更新年假
//    @PutMapping("/check/{loginNo,empNo,annualLeave}")
//    Check undateAnnualLeave(String loginNo, String empNo, int annualLeave){
//        String userPower = userService.getUserPower(loginNo);
//        Power power = powerService.getPower(userPower);
//        //有权限才可以审批
//        if(power.getAnnualLeavePower()){
//            return checkService.updateAnnualLeave(empNo,annualLeave);
//        }else {
//            return null;
//        }
//    }

}
