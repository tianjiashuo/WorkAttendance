package com.workattendance.Service;

import com.workattendance.Repository.dao.UserDao;
import com.workattendance.Repository.entity.Absence;
import com.workattendance.Repository.entity.GoOut;
import com.workattendance.Repository.entity.Leave;
import com.workattendance.Repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
@Service("userService")
public class UserService {

    @Autowired
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

    /***
     * 获得所有员工的状态
     * @param date
     * @return
     */
    public HashMap<String,List> getAllstate(String date){
      HashMap<String,List> allState = new HashMap<>();

      if (userDao.getAllLeaveState(date,"带薪休假")!=null){
          List<Absence> userPaidLeave =Absence.toAbsenceList(userDao.getAllLeaveState(date,"带薪休假"));
          allState.put("带薪休假",userPaidLeave);
      }

      if(userDao.getAllLeaveState(date,"无薪休假")!=null){
          List<Absence> userUnpaidLeave = Absence.toAbsenceList(userDao.getAllLeaveState(date,"无薪休假"));
          allState.put("无薪休假",userUnpaidLeave);
      }

      if(userDao.getAllGoOutState(date)!=null){
          List<Absence> userOut = Absence.toAbsenceList(userDao.getAllGoOutState(date));
          allState.put("外出",userOut);
      }

      if(userDao.getAllInCompanyState(date)!=null){
          List<String> userInCompany = User.onlyName(userDao.getAllInCompanyState(date));
          allState.put("在公司",userInCompany);
      }
      return allState;
    }
}
