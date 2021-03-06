package com.workattendance.Service;

import com.workattendance.Controller.UserVo;
import com.workattendance.Repository.dao.GoOutDao;
import com.workattendance.Repository.dao.LeaveDao;
import com.workattendance.Repository.dao.UserDao;
import com.workattendance.Repository.entity.Absence;
import com.workattendance.Repository.entity.GoOut;
import com.workattendance.Repository.entity.Leave;
import com.workattendance.Repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service("userService")
@EnableScheduling
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private LeaveDao leaveDao;
    @Autowired
    private GoOutDao goOutDao;


    //得到员工权限信息
    public int getUserPower(String empNo){
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

    /***
     * 请假/外出生效后更新员工状态(定时函数:每天凌晨00：25运行)
     * @author shuo
     * @return
     */
    @Scheduled(cron="0 25 0 1/1 * ? ")
    public void updateUserStateStart(){
        //获得当天00:00时间戳
        long nowTime =System.currentTimeMillis();
        long startTime = nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L));
        long endTime = startTime + 24*60*60*1000;
        List<Leave> leaveList = leaveDao.queryAllLeave(convertTimeToString(startTime),convertTimeToString(endTime));
        for (int i = 0; i < leaveList.size(); i++) {
            if(convertTimeToLong(leaveList.get(i).getStart_time()) >= startTime &&convertTimeToLong(leaveList.get(i).getStart_time())<=endTime){
                if(leaveDao.queryLeaveSalary(leaveList.get(i).getType())){
                    userDao.updateUserStateByEmpNo(leaveList.get(i).getEmp_no(),"带薪休假");
                }
                else{
                    userDao.updateUserStateByEmpNo(leaveList.get(i).getEmp_no(),"无薪休假");
                }
            }
        }
        List<GoOut> goOutList = goOutDao.queryAllGoOut(convertTimeToString(startTime),convertTimeToString(endTime));
        for (int i = 0; i < goOutList.size(); i++) {
            if(convertTimeToLong(goOutList.get(i).getStart_time()) >= startTime && convertTimeToLong(goOutList.get(i).getStart_time())<=endTime){
                userDao.updateUserStateByEmpNo(goOutList.get(i).getEmp_no(),"外出");
            }
        }
    }
    /***
     * 请假/外出到期后更新员工状态(定时函数:每天凌晨00：30运行)
     * @author shuo
     * @return
     */
    @Scheduled(cron="0 30 0 1/1 * ? ")
    public void updateUserState(){
        //获得当天00:00时间戳
        long nowTime =System.currentTimeMillis();
        long endTime = nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L));
        long startTime = endTime - 24*60*60*1000;
        List<Leave> leaveList = leaveDao.queryAllLeave(convertTimeToString(startTime),convertTimeToString(endTime));
        for (int i = 0; i < leaveList.size(); i++) {
            if(convertTimeToLong(leaveList.get(i).getEnd_time()) <= endTime){
                userDao.updateUserStateByEmpNo(leaveList.get(i).getEmp_no(),"在公司");
            }
        }
        List<GoOut> goOutList = goOutDao.queryAllGoOut(convertTimeToString(startTime),convertTimeToString(endTime));
        for (int i = 0; i < goOutList.size(); i++) {
            if(convertTimeToLong(goOutList.get(i).getEnd_time()) <= endTime){
                userDao.updateUserStateByEmpNo(goOutList.get(i).getEmp_no(),"在公司");
            }
        }
    }

    //时间戳转datetime
    public static String convertTimeToString(Long time) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }
    //datetime转时间戳
    public static Long convertTimeToLong(String time) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(time, ftf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /***
     * 员工修改密码
     * @author bai
     * @return
     */
    public Boolean updatePasswordByEmpNo(String empNo, String passwords) {
        String temp = passwords.split(":|,")[1];
        String oldPassword = temp.substring(1);
        String newPassword = passwords.split(":|}")[2];
        return userDao.updatePasswordByEmpNo(empNo, oldPassword,newPassword);
    }
}
