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
        check.setDays(rs.getInt("days"));
        check.setEmpName(rs.getString("emp_name"));
        check.setAbsenteesim(rs.getInt("absenteeism"));
        check.setLate_days(rs.getInt("late_days"));
        check.setLeave_early_days(rs.getInt("leave_early_days"));
        check.setDate(rs.getString("date"));
        return check;
    }
}
