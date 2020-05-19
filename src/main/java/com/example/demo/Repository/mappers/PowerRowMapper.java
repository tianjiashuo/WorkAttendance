package com.example.demo.Repository.mappers;

import com.example.demo.Repository.entity.Power;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PowerRowMapper implements RowMapper<Power> {

    @Override
    public Power mapRow(ResultSet rs, int i) throws SQLException {
        Power power = new Power();
        power.setId(rs.getInt("id"));
        power.setPower(rs.getString("power"));
        power.setViewLeave(rs.getBoolean("viewLeave"));
        power.setViewAttendance(rs.getBoolean("viewAttendance"));
        power.setViewGoOut(rs.getBoolean("viewGoOut"));
        power.setAnnualLeavePower(rs.getBoolean("annualLeavePower"));
        power.setLeavePower(rs.getBoolean("leavePower"));
        power.setGoOutPower(rs.getBoolean("goOutPoweR"));
        return power;
    }
}
