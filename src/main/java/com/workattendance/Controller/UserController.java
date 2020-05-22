package com.workattendance.Controller;

import com.workattendance.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //登录
    @GetMapping("/login/{empNo,password}")
    Boolean login(@PathVariable String empNo, String password) {
        return userService.Login(empNo,password);
    }


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

}
