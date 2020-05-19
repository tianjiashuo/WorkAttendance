package com.example.demo.Controller;

        import com.example.demo.Repository.entity.Check;
        import com.example.demo.Repository.entity.Leave;
        import com.example.demo.Repository.entity.Power;
        import com.example.demo.Service.CheckService;
        import com.example.demo.Service.PowerService;
        import com.example.demo.Service.UserService;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PutMapping;

        import java.util.List;

public class CheckController {

    private CheckService checkService;
    private PowerService powerService;
    private UserService userService;

    //更新年假
    @PutMapping("/check/{loginNo,empNo,annualLeave}")
    Check undateAnnualLeave(String loginNo, String empNo, int annualLeave){
        String userPower = userService.getUserPower(loginNo);
        Power power = powerService.getPower(userPower);
        //有权限才可以审批
        if(power.getAnnualLeavePower()){
            return checkService.updateAnnualLeave(empNo,annualLeave);
        }else {
            return null;
        }
    }

}
