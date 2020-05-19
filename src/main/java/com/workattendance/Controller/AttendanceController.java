package com.workattendance.Controller;

import com.workattendance.Repository.entity.Attendance;
import com.workattendance.Repository.entity.Power;
import com.workattendance.Service.AttendanceService;
import com.workattendance.Service.CheckService;
import com.workattendance.Service.PowerService;
import com.workattendance.Service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class AttendanceController {

    private AttendanceService attendanceService;
    private PowerService powerService;
    private UserService userService;
    private CheckService checkService;

    //出勤打卡
    @PostMapping("/attendance")
    Attendance addAttendance(@RequestBody Attendance attendance) {
        //缺勤则缺勤次数加一
        if(attendance.getState().equals("缺勤")){
            int absenteeism = checkService.getAbsenteeism(attendance.getEmpNo());
            absenteeism += 1;
            checkService.updateAbsenteeism(attendance.getEmpNo(),absenteeism);
        }
        return attendanceService.insertAttendance(attendance);
    }

    //查询自己的出勤记录
    @GetMapping("/attendance/{empNo}")
    List<Attendance> getAttendanceByEmpNo(@PathVariable String empNo) {
        return attendanceService.queryAttendanceByEmpNo(empNo);
    }

    //查询所有人的出勤记录
    @GetMapping("/attendance/{empNo}")
    List<Attendance>  getAttendance(String empNo) {
        String userPower = userService.getUserPower(empNo);
        Power power = powerService.getPower(userPower);
        //有权限才可以查看
        if(power.getViewAttendance()){
            return attendanceService.queryAttendance();
        }else{
            return null;
        }
    }

}
