package com.workattendance.Service;

import com.workattendance.Repository.dao.CheckDao;
import com.workattendance.Repository.entity.Check;
import com.workattendance.Repository.entity.GoOut;
import com.workattendance.Repository.entity.Leave;
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

    //更新年假
    public Check updateAnnualLeave(String empNo, int annualLeave){
        return checkDao.updateAnnualLeaveByEmpNo(empNo,annualLeave);
    }


    //更新请假次数
    public Check updateLeaveTime(String empNo, int leaveTime){
        return checkDao.updateLeaveTimeByEmpNo(empNo,leaveTime);
    }


    //更新缺勤次数
    public Check updateAbsenteeism(String empNo, int absenteeism){
        return checkDao.updateAbsenteeismByEmpNo(empNo,absenteeism);
    }


    /***
     * 根据日期查询check
     * @author mao
     * @param from
     * @param end
     * @return
     */
    public List<Check> getALLCheckByDate(String from,String end){
        return checkDao.queryAllCheckByDay(from,end);
    }

    /***
     * 每晚00：30更新check
     * @author shuo
     * @return
     */
    @Scheduled(cron="0 30 0 1/1 * ? ")
    public void updateCheckState(){

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
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()/1000;
    }

}
