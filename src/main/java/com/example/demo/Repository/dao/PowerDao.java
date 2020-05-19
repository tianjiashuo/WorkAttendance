package com.example.demo.Repository.dao;

/*权限*/

import com.example.demo.Repository.entity.Power;
import com.example.demo.Repository.mappers.PowerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class PowerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //根据员工权限信息查询员工操作权限
    public Power queryPowerByEmpPower(String power) {
        String sql = "SELECT * FROM power WHERE power=? ";
        Power pow= jdbcTemplate.queryForObject(sql, new PowerRowMapper(), power);
        return pow;
    }
}
