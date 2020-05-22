package com.workattendance.Controller;

import com.workattendance.Repository.entity.Check;
import com.workattendance.Repository.entity.Power;
import com.workattendance.Service.CheckService;
import com.workattendance.Service.PowerService;
import com.workattendance.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CheckController {

    @Autowired
    private CheckService checkService;

//    private PowerService powerService;
//    private UserService userService;

//    //更新年假
//    @PutMapping("/check/{loginNo,empNo,annualLeave}")
//    Check undateAnnualLeave(String loginNo, String empNo, int annualLeave){
//        String userPower = userService.getUserPower(loginNo);
//        Power power = powerService.getPower(userPower);
//        //有权限才可以审批
////        if(power.getAnnualLeavePower()){
////            return checkService.updateAnnualLeave(empNo,annualLeave);
////        }else {
////            return null;
////        }
//        return null;
//    }

    /***
     * 按照时间查询check
     * @author mao
     * @param fromDate
     * @param endDate
     * @return
     */
    @GetMapping("/check/byDate/{fromDate}/{endDate}")
    List<Check> getALLCheckByDate (@PathVariable  String fromDate, @PathVariable String endDate){
        return checkService.getALLCheckByDate(fromDate,endDate);
    }
}
