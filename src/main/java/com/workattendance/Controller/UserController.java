package com.workattendance.Controller;

import com.workattendance.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;




    /***
     * 获得所有人的状态
     * @author mao
     * @return
     */
    @GetMapping("/user/getAllState")
    HashMap<String, List> getAllState(){
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  userService.getAllstate(df.format(day));
    }

    /***
     * 修改密码,此处oldPassword是哈希加密后的
     * @author bai
     * @return
     */
    @PutMapping("/user/{empNo}")
    public Boolean updatePasswordByEmpNo(@PathVariable String empNo, @RequestBody String passwords){
        return userService.updatePasswordByEmpNo(empNo,passwords);
    }

}
