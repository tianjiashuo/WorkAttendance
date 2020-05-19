package com.example.demo.Service;

import com.example.demo.Repository.dao.AttendanceDao;
import com.example.demo.Repository.entity.Attendance;

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
