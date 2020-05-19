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
        attendance.setEmpNo(rs.getString("empNo"));
        attendance.setDate(rs.getString("date"));
        attendance.setStartTime(rs.getString("startTime"));
        attendance.setEndTime(rs.getString("endTime"));
        attendance.setState(rs.getString("state"));
        return attendance;
    }
}
