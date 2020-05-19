package com.workattendance.Service;

import com.workattendance.Repository.dao.UserDao;
import com.workattendance.Repository.entity.User;

public class UserService {

    private UserDao userDao;

    //登录
    public boolean Login (String empNo, String password){
        User user = userDao.queryUserByEmpNo(empNo);
        //判断密码是否正确
        if(user.getPassword().equals(password)){
            return true;
        }else{
            return false;
        }
    }

    //得到员工权限信息
    public String getUserPower(String empNo){
        User user = userDao.queryUserByEmpNo(empNo);
        return user.getPower();
    }

}
