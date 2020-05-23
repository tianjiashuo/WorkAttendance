package com.workattendance.Controller;

import com.workattendance.Repository.entity.Attendance;
import com.workattendance.Repository.entity.Power;
import com.workattendance.Service.AttendanceService;
import com.workattendance.Service.CheckService;
import com.workattendance.Service.PowerService;
import com.workattendance.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;
    private PowerService powerService;
    private UserService userService;
    private CheckService checkService;

//    //出勤打卡
//    @PostMapping("/attendance")
//    Attendance addAttendance(@RequestBody Attendance attendance) {
//        //缺勤则缺勤次数加一
//        if(attendance.getState().equals("缺勤")){
//            int absenteeism = checkService.getAbsenteeism(attendance.getEmpNo());
//            absenteeism += 1;
//            checkService.updateAbsenteeism(attendance.getEmpNo(),absenteeism);
//        }
//        return attendanceService.insertAttendance(attendance);
//    }

    /***
     * 查询自己的打卡记录
     * @author mao
     * @param empNo
     * @return
     */
    @GetMapping("/attendance/byEmp/{empNo}")
    List<Attendance> getAttendanceByEmpNo(@PathVariable String empNo) {
        return attendanceService.queryAttendanceByEmpNo(empNo);
    }


    /***
     * 查询所有人的出勤记录
     * @author mao
     * @return
     */
    @GetMapping("/attendance/all")
    List<Attendance>  getAttendance() {

        return attendanceService.queryAllAttendance();
    }

    /***
     * 按照日期查询
     * @author mao
     * @param fromDate
     * @param endDate
     * @return
     */
    @GetMapping("/attendance/byDate/{fromDate}/{endDate}")
    List<Attendance> getAttendanceByDate(@PathVariable String fromDate,@PathVariable String endDate){
        return attendanceService.queryAttendanceByDate(fromDate,endDate);
    }


}
