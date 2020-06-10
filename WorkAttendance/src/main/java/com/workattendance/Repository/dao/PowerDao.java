package com.workattendance.Repository.dao;

/*权限*/

import com.workattendance.Repository.entity.Power;
import com.workattendance.Repository.mappers.PowerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("powerDao")
public class PowerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //根据员工权限信息查询员工操作权限
    public Power queryPowerByEmpPower(String power) {
        String sql = "SELECT * FROM power WHERE power=? ";
        Power pow= jdbcTemplate.queryForObject(sql, new PowerRowMapper(), power);
        return pow;
    }

    //根据权限id查询是否具有某权限
    public boolean queryViewOwnAttendancePowerById(int powerId) {
        String sql = "SELECT `view_own_attendance` FROM power WHERE id=? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{powerId}, Boolean.class);
    }

    public boolean queryViewAllAttendancePowerById(int powerId) {
        String sql = "SELECT `view_all_attendance` FROM power WHERE id=? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{powerId}, Boolean.class);
    }

    public boolean queryViewCheckPowerById(int powerId) {
        String sql = "SELECT `view_check` FROM power WHERE id=? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{powerId}, Boolean.class);
    }

    public boolean queryViewAllLeavePowerById(int powerId) {
        String sql = "SELECT `view_all_leave` FROM power WHERE id=? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{powerId}, Boolean.class);
    }

    public boolean queryViewPassLeavePowerById(int powerId) {
        String sql = "SELECT `view_pass_leave` FROM power WHERE id=? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{powerId}, Boolean.class);
    }

    public boolean queryViewAllGooutPowerById(int powerId) {
        String sql = "SELECT `view_all_goout` FROM power WHERE id=? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{powerId}, Boolean.class);
    }

    public boolean queryViewPassGooutPowerById(int powerId) {
        String sql = "SELECT `view_pass_goout` FROM power WHERE id=? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{powerId}, Boolean.class);
    }

    public boolean querySetAnnualGooutPowerById(int powerId) {
        String sql = "SELECT `set_annual_goout` FROM power WHERE id=? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{powerId}, Boolean.class);
    }

    public boolean querySetLeaveTypesPowerById(int powerId) {
        String sql = "SELECT `set_leave_types` FROM power WHERE id=? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{powerId}, Boolean.class);
    }

    public boolean queryLeaveApprovalPowerById(int powerId) {
        System.out.println("lla");
        String sql = "SELECT `leave_approval` FROM power WHERE id=? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{powerId}, Boolean.class);
    }

    public boolean queryGooutApprovalPowerById(int powerId) {
        String sql = "SELECT `goout_approval` FROM power WHERE id=? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{powerId}, Boolean.class);
    }
}
