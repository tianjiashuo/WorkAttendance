package com.workattendance.Repository.mappers;

import com.workattendance.Repository.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//mao增加了user新增属性的封装
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setEmpNo(rs.getString("emp_no"));
        user.setEmpName(rs.getString("emp_name"));
        user.setPassword(rs.getString("password"));
        user.setPower(rs.getString("power"));
        user.setStete(rs.getString("state"));
        user.setYears(rs.getString("years"));
        return user;
    }
}
