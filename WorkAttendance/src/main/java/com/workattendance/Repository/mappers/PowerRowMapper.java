package com.workattendance.Repository.mappers;

import com.workattendance.Repository.entity.Power;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PowerRowMapper implements RowMapper<Power> {

    @Override
    public Power mapRow(ResultSet rs, int i) throws SQLException {
        Power power = new Power();
        power.setId(rs.getInt("id"));
        power.setPositionId(rs.getInt("positionId"));
        power.setView_own_attendance(rs.getBoolean("view_own_attendance"));
        power.setView_all_attendance(rs.getBoolean("view_all_attendance"));
        power.setView_check(rs.getBoolean("view_check"));
        power.setView_all_leave(rs.getBoolean("view_all_leave"));
        power.setView_all_leave(rs.getBoolean("view_all_goout"));
        power.setView_pass_leave(rs.getBoolean("view_pass_leave"));
        power.setView_pass_goout(rs.getBoolean("view_pass_goout"));
        power.setSet_annual_leave(rs.getBoolean("set_annual_leave"));
        power.setSet_leave_types(rs.getBoolean(" set_leave_types"));
        power.setLeave_approval(rs.getBoolean("leave_approval"));
        power.setGoout_approval(rs.getBoolean("goout_approval"));
        return power;
    }
}
