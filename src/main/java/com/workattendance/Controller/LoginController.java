package com.workattendance.Controller;

import com.workattendance.Service.LogInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LogInService loginService;
    @RequestMapping("/doLogin")
    public String userLogin(@RequestBody UserVo user){
        System.out.println("User : " + user);
        String str = "error";
        boolean bool =loginService.Login(user);
        if (bool ) {
            str = "ok";
        }
        return str;
    }
}
