package com.workattendance.Repository.mappers;

import com.workattendance.Repository.entity.Attendance;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceRowMapper implements RowMapper<Attendance> {

    @Override
    public Attendance mapRow(ResultSet rs, int i) throws SQLException {
        Attendance attendance = new Attendance();
        attendance.setId(rs.getInt("id"));
        attendance.setEmp_no(rs.getString("emp_no"));
        attendance.setEmp_name(rs.getString("emp_name"));
        attendance.setTime(rs.getString("time"));
        return attendance;
    }
}
