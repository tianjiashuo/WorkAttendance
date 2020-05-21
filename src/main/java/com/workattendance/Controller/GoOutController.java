package com.workattendance.Controller;

import com.workattendance.Repository.entity.GoOut;
import com.workattendance.Repository.entity.Power;
import com.workattendance.Service.GoOutService;
import com.workattendance.Service.PowerService;
import com.workattendance.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoOutController {

    @Autowired
    private GoOutService goOutService;
    private PowerService powerService;
    private UserService userService;

    //外出申请ok
    @PostMapping("/goOut")
    GoOut inserGoOut(@RequestBody GoOut goOut) {
        return goOutService.inserGoOut(goOut);
    }

    //查询自己的外出记录ok
    @GetMapping("/goOut/{empNo}")
    List<GoOut> getGoOutByEmpNo(@PathVariable int empNo) {
        return goOutService.queryGoOutByEmpNo(empNo);
    }

    //修改申请ok
    @PutMapping("/goOut/{id}")
    public GoOut updateGoOutById(@PathVariable int id, @RequestBody GoOut goOut){
        return goOutService.updateGoOutById(id, goOut);
    }

    //删除申请ok
    @DeleteMapping("/goOut/{id}")
    public void deleteGoOutById(@PathVariable int id){
        goOutService.deleteGoOutById(id);
    }

//    //查询所有人的外出记录
//    @GetMapping("/goOut/{empNo}")
//    List<GoOut>  getGoOut(String empNo) {
//        String userPower = userService.getUserPower(empNo);
//        Power power = powerService.getPower(userPower);
//        //有权限才可以查看
//        if(power.getViewGoOut()){
//            return goOutService.queryGoOut();
//        }else{
//            return null;
//        }
//    }

//    //审批申请
//    @PutMapping("/goOut/{empNo,id,goOut}")
//    GoOut examineById(String empNo, int id,GoOut goOut){
//        String userPower = userService.getUserPower(empNo);
//        Power power = powerService.getPower(userPower);
//        //有权限才可以审批
//        if(power.getGoOutPower()){
//            return goOutService.examineById(id, goOut);
//        }else {
//            return null;
//        }
//    }
}
