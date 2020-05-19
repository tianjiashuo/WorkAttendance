package com.workattendance.Service;

import com.workattendance.Repository.dao.AttendanceDao;
import com.workattendance.Repository.entity.Attendance;

import java.util.List;

public class AttendanceService {

    private AttendanceDao attendanceDao;

    //添加打卡记录
    public Attendance insertAttendance(Attendance attendance){
        return attendanceDao.insert(attendance);
    }

    //查询自己的出勤记录
    public List<Attendance> queryAttendanceByEmpNo (String empNo){
        return attendanceDao.queryAttendanceByEmpNo(empNo);
    }

    //查询所有人的出勤记录
    public List<Attendance> queryAttendance(){
        return attendanceDao.queryAttendance();
    }
}
