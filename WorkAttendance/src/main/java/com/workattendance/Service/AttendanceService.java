package com.workattendance.Service;

import com.workattendance.Repository.dao.AttendanceDao;
import com.workattendance.Repository.dao.UserDao;
import com.workattendance.Repository.entity.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("attendanceServer")
public class AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;


//    //添加打卡记录
//    public Attendance insertAttendance(Attendance attendance){
//        return attendanceDao.insert(attendance);
//    }

    /***
     * 查询自己的打卡纪录
     * @param empNo
     * @return
     */
    public List<Attendance> queryAttendanceByEmpNo (String empNo){

        return attendanceDao.queryAttendanceByEmpNo(empNo);
    }

    /***
     * 查询所有人考勤纪录
     * @author mao
     * @return
     */
    public List<Attendance> queryAllAttendance(){
        return attendanceDao.queryAllAttendance();
    }

    /***
     * 按日期查打卡假纪录
     * @author mao
     * @param fromDate
     * @param endDate
     * @return
     */
    public List<Attendance> queryAttendanceByDate(String fromDate,String endDate){
        return attendanceDao.queryAllAttendanceByDate(fromDate,endDate);
    }

    /***
     * 按人查看打卡记录
     * @author mao
     * @param emp_id
     * @return
     */
    public List<Attendance> queryAttendanceByEmpId(String emp_id){
        return attendanceDao.queryAttendanceByEmpNo(emp_id);
    }



}
