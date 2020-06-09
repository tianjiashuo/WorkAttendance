package com.workattendance.Service;

import com.workattendance.Controller.UserVo;
import com.workattendance.Repository.dao.UserDao;
import com.workattendance.Repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("LogInService")
public class LogInService {

        @Autowired
        private UserDao userDao;

        //登录
        public boolean Login (UserVo userVo){
            User user = userDao.queryUserByEmpNo(userVo.getEmpId());
            //判断密码是否正确
            if(user.getPassword().equals(userVo.getPassWord())){
                UserBo userbo = UserBo.getUserBo();
                userbo.setEmpName(user.getEmpName());
                userbo.setEmpNo(user.getEmpNo());
                userbo.setId(user.getId());
                userbo.setPower(user.getPower());
                return true;
            }else{
                return false;
            }
        }
}
