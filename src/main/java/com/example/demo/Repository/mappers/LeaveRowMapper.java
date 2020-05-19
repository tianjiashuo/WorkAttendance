package com.example.demo.Repository.mappers;

import com.example.demo.Repository.entity.Leave;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LeaveRowMapper implements RowMapper<Leave> {

    @Override
    public Leave mapRow(ResultSet rs, int i) throws SQLException {
        Leave leave = new Leave();
        leave.setId(rs.getInt("id"));
        leave.setEmpNo(rs.getString("empNo"));
        leave.setEmpName(rs.getString("empName"));
        leave.setType(rs.getString("type"));
        leave.setStartTime(rs.getString("startTime"));
        leave.setEndTime(rs.getString("endTime"));
        leave.setReason(rs.getString("reason"));
        leave.setState(rs.getString("state"));
        leave.setOpinion(rs.getString("opinion"));
        return leave;
    }
}
