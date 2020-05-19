package com.example.demo.Repository.mappers;

import com.example.demo.Repository.entity.Attendance;
import com.example.demo.Repository.entity.Check;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckRowMapper implements RowMapper<Check> {

    @Override
    public Check mapRow(ResultSet rs, int i) throws SQLException {
        Check check = new Check();
        check.setId(rs.getInt("id"));
        check.setEmpNo(rs.getString("empNo"));
        check.setEmpName(rs.getString("empName"));
        check.setAnnualLeave(rs.getInt("annualLeave"));
        check.setLeaveTime(rs.getInt("leaveTime"));
        check.setAbsenteeism(rs.getInt("absenteeism"));
        return check;
    }
}
