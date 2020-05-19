package com.example.demo.Controller;

import com.example.demo.Repository.entity.GoOut;
import com.example.demo.Repository.entity.Power;
import com.example.demo.Service.GoOutService;
import com.example.demo.Service.PowerService;
import com.example.demo.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class GoOutController {

    private GoOutService goOutService;
    private PowerService powerService;
    private UserService userService;

    //外出申请
    @PostMapping("/goOut")
    GoOut inserGoOut(@RequestBody GoOut goOut) {
        return goOutService.inserGoOut(goOut);
    }

    //查询自己的外出记录
    @GetMapping("/goOut/{empNo}")
    List<GoOut> getGoOutByEmpNo(@PathVariable String empNo) {
        return goOutService.queryGoOutByEmpNo(empNo);
    }

    //修改申请
    @PutMapping("/goOut/{id,goOut}")
    public GoOut updateGoOutById(int id,GoOut goOut){
        return goOutService.updateGoOutById(id,goOut);
    }

    //删除申请
    @DeleteMapping("/goOut/{id}")
    public void deleteGoOutById(int id){
        goOutService.deleteGoOutById(id);
    }

    //查询所有人的外出记录
    @GetMapping("/goOut/{empNo}")
    List<GoOut>  getGoOut(String empNo) {
        String userPower = userService.getUserPower(empNo);
        Power power = powerService.getPower(userPower);
        //有权限才可以查看
        if(power.getViewGoOut()){
            return goOutService.queryGoOut();
        }else{
            return null;
        }
    }

    //审批申请
    @PutMapping("/goOut/{empNo,id,goOut}")
    GoOut examineById(String empNo, int id,GoOut goOut){
        String userPower = userService.getUserPower(empNo);
        Power power = powerService.getPower(userPower);
        //有权限才可以审批
        if(power.getGoOutPower()){
            return goOutService.examineById(id, goOut);
        }else {
            return null;
        }
    }
}
