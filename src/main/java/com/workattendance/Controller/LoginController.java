package com.workattendance.Controller;

public class LoginController {
    private String userId;
    private String password;
    private String token;

    public LoginController(String userId,String password,String token){
        this.userId = userId;
        this.password = password;
        this.token = token;
    }


}
