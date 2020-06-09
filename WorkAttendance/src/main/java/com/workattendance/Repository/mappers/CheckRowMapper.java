package com.workattendance.Repository.mappers;

import com.workattendance.Repository.entity.Check;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckRowMapper implements RowMapper<Check> {

    @Override
    public Check mapRow(ResultSet rs, int i) throws SQLException {
        Check check = new Check();
        check.setId(rs.getInt("id"));
        check.setEmpNo(rs.getString("emp_no"));
        check.setEmpName(rs.getString("emp_name"));
        check.setLeaveDays(rs.getInt("leave_days"));
        check.setAbsenteeismDays(rs.getInt("absenteeism_days"));
        check.setLateDays(rs.getInt("late_days"));
        check.setLeaveEarlyDays(rs.getInt("leave_early_days"));
        check.setDate(rs.getString("date"));
        return check;
    }
}
