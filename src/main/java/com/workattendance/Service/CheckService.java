package com.workattendance.Service;

import com.workattendance.Repository.dao.*;
import com.workattendance.Repository.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TimeZone;

@Service("checkService")
@EnableScheduling
public class CheckService {

    @Autowired
    private CheckDao checkDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private AttendanceDao attendanceDao;
    @Autowired
    private PowerDao powerDao;

    /***
     * 根据日期查询check
     * @author mao
     * @param from
     * @param end
     * @return
     */
    public List<Check> getALLCheckByDate(String from,String end){

        if(powerDao.queryViewCheckPowerById(UserBo.getUserBo().getPower())){
            return checkDao.queryAllCheckByDay(from,end);
        }else{
            return null;
        }

    }

    //查询某天某位用户的check
    public Check getYesterdayCheck(String empNo,String date){
        String pureDate  = date.split(" ")[0]+"%";
        Check check =checkDao.queryCheckByEmpNo(empNo,pureDate);
        return check;
    }

    //月初清零
    public void toZero(Check check,String date){
        if(date.split(" ")[0].split("-")[2].equals("1")){
            check.setLeaveDays(0);
            check.setAbsenteeismDays(0);
            check.setLateDays(0);
            check.setLeaveEarlyDays(0);
        }
    }

    /***
     * 每晚00：30更新check
     * @author shuo
     * @return
     */
    @Scheduled(cron="0 20 00 1/1 * ? ")
    public void updateCheckState(){
        List<User> userList = userDao.getAllUser();
        for (int i = 0; i < userList.size(); i++) {
            //获得当天00:00时间戳
            long nowTime =System.currentTimeMillis();
            long endTime = nowTime - ((nowTime + TimeZone.getDefault().getRawOffset()) % (24 * 60 * 60 * 1000L));
            //昨天天00：00时间戳
            long startTime = endTime - 24*60*60*1000;
            long yesterday =startTime - 24*60*60*1000;
            //取出该员工前一天早晚打卡记录
            String[] attendances =  getUserAttendance(userList.get(i).getEmpNo(),startTime,endTime);
            //获取前一天应有状态
            String state = userDao.queryUserByEmpNo(userList.get(i).getEmpNo()).getStete();
            Check yesterdayCheck = getYesterdayCheck(userList.get(i).getEmpNo(),convertTimeToString(yesterday));
            //月初归零
            toZero(yesterdayCheck,convertTimeToString(startTime));
            //更新日期
            yesterdayCheck.setDate(convertTimeToString(startTime));
            Check todayCheck = yesterdayCheck;
            if(state.equals("无薪休假")){
                //leave_days+1
                todayCheck.setLeaveDays(todayCheck.getLeaveDays()+1);
                checkDao.updateCheck(todayCheck);
            }
            else if(state.equals("带薪休假")||state.equals("外出")){
                //normal
                checkDao.updateCheck(todayCheck);
            }
            else if(state.equals("在公司")){
                if(attendances != null){
                    Integer morningHour = Integer.parseInt(attendances[0].substring(11, 13));
                    Integer afternoonHour = Integer.parseInt(attendances[1].substring(11, 13));
                    if(morningHour>=9){
                        //迟到
                        todayCheck.setLateDays(todayCheck.getLateDays()+1);
                    }else if(afternoonHour<17){
                        //早退
                        todayCheck.setLeaveEarlyDays(todayCheck.getLeaveDays()+1);
                    }
                    checkDao.updateCheck(todayCheck);
                }else{
                    //缺勤
                    todayCheck.setAbsenteeismDays(todayCheck.getAbsenteeismDays()+1);
                    checkDao.updateCheck(todayCheck);
                }
            }
        }
    }

    //获取某位用户当天最早最晚打卡记录
    public String[] getUserAttendance(String empNo, Long startTime,Long endTime){
        String[] result = new String[2];
        //取出该员工前一天所有打卡记录
        List<Attendance> attendanceList = attendanceDao.queryAttendanceByEmpNoDate(empNo,convertTimeToString(startTime),convertTimeToString(endTime));
        //找出记录中最早和最晚
        if(attendanceList.size()>1){
            Long moring = convertTimeToLong(attendanceList.get(0).getTime());
            Long afternoon = convertTimeToLong(attendanceList.get(0).getTime());
            for (int j = 1; j < attendanceList.size(); j++) {
                Long tempTime = convertTimeToLong(attendanceList.get(j).getTime());
                if(tempTime < moring){
                    moring = tempTime;
                }
                else if(tempTime > afternoon){
                    afternoon = tempTime;
                }
            }
            result[0] = convertTimeToString(moring);
            result[1] = convertTimeToString(afternoon);
            System.out.println(result[0]);
            System.out.println(result[1]);
        }
        else{
            return null;
        }
        return result;
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

}
