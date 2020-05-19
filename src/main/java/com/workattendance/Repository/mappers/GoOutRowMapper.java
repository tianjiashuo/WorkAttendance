package com.workattendance.Repository.mappers;

import com.workattendance.Repository.entity.GoOut;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoOutRowMapper implements RowMapper<GoOut> {

    @Override
    public GoOut mapRow(ResultSet rs, int i) throws SQLException {
        GoOut goOut = new GoOut();
        goOut.setId(rs.getInt("id"));
        goOut.setEmpNo(rs.getString("empNo"));
        goOut.setEmpName(rs.getString("empName"));
        goOut.setStartTime(rs.getString("startTime"));
        goOut.setEndTime(rs.getString("endTime"));
        goOut.setReason(rs.getString("reason"));
        goOut.setState(rs.getString("state"));
        goOut.setOpinion(rs.getString("opinion"));
        return goOut;
    }
}
