package com.workattendance.Controller;

import com.fasterxml.jackson.annotation.JsonProperty;
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


    /***
     * 外出申请
     * @author bai
     * @return
     */
    @PostMapping("/goOut")
    GoOut inserGoOut(@RequestBody GoOut goOut) {
        return goOutService.inserGoOut(goOut);
    }

    /***
     * 查询自己的外出记录
     * @author bai
     * @return
     */
    @GetMapping("/goOut/{empNo}")
    List<GoOut> getGoOutByEmpNo(@PathVariable int empNo) {
        return goOutService.queryGoOutByEmpNo(empNo);
    }

    /***
     * 根据id查询外出记录详情
     * @author bai
     * @return
     */
    @GetMapping("/goOutDetail/{id}")
    GoOut getGoOutById(@PathVariable int id) {
        return goOutService.queryGoOutById(id);
    }

    /***
     * 修改申请
     * @author bai
     * @return
     */
    @PutMapping("/goOut/{id}")
    public GoOut updateGoOutById(@PathVariable int id, @RequestBody GoOut goOut){
        return goOutService.updateGoOutById(id, goOut);
    }

    /***
     * 删除申请
     * @author bai
     * @return
     */
    @DeleteMapping("/goOut/{id}")
    public void deleteGoOutById(@PathVariable int id){
        goOutService.deleteGoOutById(id);
    }

    /***
     * 项目经理审批外出申请
     * @author shuo
     * @return
     */
    @PutMapping("/goOutAuditDivision/{id}")
    public void auditByDivision(@PathVariable int id,@RequestBody GoOut response){
        goOutService.auditByDivision(id,response);
    }

    /***
     * 副经理审批外出申请
     * @author shuo
     * @return
     */
    @PutMapping("/goOutAuditVice/{id}")
    public void auditByVice(@PathVariable int id,@RequestBody GoOut response){
        goOutService.auditByVice(id,response);
    }
    
    /***
     * 总经理审批外出申请
     * @author shuo
     * @return
     */
    @PutMapping("/goOutAuditManager/{id}")
    public void auditByManager(@PathVariable int id,@RequestBody GoOut response){
        goOutService.auditByManager(id,response);
    }

}
