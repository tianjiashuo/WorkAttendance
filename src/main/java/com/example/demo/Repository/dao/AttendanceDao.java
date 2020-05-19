package com.example.demo.Repository.dao;

/*出勤*/

import com.example.demo.Repository.entity.Attendance;
import com.example.demo.Repository.mappers.AttendanceRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AttendanceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //添加出勤记录
    public Attendance insert(Attendance attendance) {
        Attendance attendance1 = new Attendance();
        String sql = "INSERT INTO attendance (id,empNo,date,startTime,endTime) VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, null, attendance.getEmpNo(), attendance.getDate(), attendance.getStartTime(), attendance.getEndTime());
        String sql2 = "SELECT * FROM attendance WHERE empNo=? ";
        attendance1= jdbcTemplate.queryForObject(sql2, new AttendanceRowMapper(), attendance.getEmpNo());

        return attendance1;
    }

    //得到单次记录
    public Attendance queryAttendanceById(int id) {
        String sql = "SELECT * FROM attendance WHERE id=? ";
        Attendance attendance= jdbcTemplate.queryForObject(sql, new AttendanceRowMapper(), id);
        return attendance;
    }

    //根据员工编号查询出勤记录
    public List<Attendance> queryAttendanceByEmpNo(String empNo) {
        String sql = "SELECT * FROM attendance WHERE empNo=? ORDER BY date";
        List<Attendance> attendanceList= jdbcTemplate.query(sql, new AttendanceRowMapper(), empNo);
        return attendanceList;
    }

    //返回所有员工出勤记录
    public List<Attendance> queryAttendance() {
        String sql = "SELECT * FROM attendance ORDER BY date";
        List<Attendance> attendanceList= jdbcTemplate.query(sql, new AttendanceRowMapper());
        return attendanceList;
    }
}
