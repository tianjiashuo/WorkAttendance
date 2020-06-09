package com.workattendance.Service;

public class LoginService {
    private String userId;
    private String password;
    private String token;

    public LoginService(String userId,String password,String token){
        this.userId = userId;
        this.password = password;
        this.token = token;
    }

    public static String createToken(){

        return null;
    }

    public boolean match(){
        return false;
    }

}
