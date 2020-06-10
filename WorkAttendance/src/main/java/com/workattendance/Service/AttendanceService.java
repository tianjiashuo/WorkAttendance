package com.workattendance.Service;

import
        com.workattendance.Repository.dao.AttendanceDao;
import com.workattendance.Repository.dao.PowerDao;
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
    @Autowired
    private PowerDao powerDao;




    /***
     * 查询自己的打卡纪录
     * @author mao
     * @return
     */
    public  List<Attendance> queryAttendance (){
        return attendanceDao.queryAttendanceByEmpNo(UserBo.getUserBo().getEmpNo());
    }

    /***
     * 查询所有人考勤纪录
     * @author mao
     * @return
     */
    public List<Attendance> queryAllAttendance(){
        if(powerDao.queryViewAllAttendancePowerById(UserBo.getUserBo().getPower())){
            return attendanceDao.queryAllAttendance();
        }else{
            return null;
        }

    }

    /***
     * 按日期查打卡假纪录
     * @author mao
     * @param fromDate
     * @param endDate
     * @return
     */
    public List<Attendance> queryAttendanceByDate(String fromDate,String endDate){


        if(powerDao.queryViewOwnAttendancePowerById(UserBo.getUserBo().getPower())){
            return attendanceDao.queryAllAttendanceByDate(fromDate,endDate);
        }else{
            return null;
        }
    }

    /***
     * 按人查看打卡记录
     * @author mao
     * @param emp_id
     * @return
     */
    public List<Attendance> queryAttendanceByEmpId(String emp_id){
        if(powerDao.queryViewAllAttendancePowerById(UserBo.getUserBo().getPower())){
            return attendanceDao.queryAttendanceByEmpNo(emp_id);
        }else{
            return null;
        }
    }



}
