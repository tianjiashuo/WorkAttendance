package com.example.demo.Repository.mappers;

import com.example.demo.Repository.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setEmpNo(rs.getString("empNo"));
        user.setEmpName(rs.getString("empName"));
        user.setPassword(rs.getString("password"));
        user.setPower(rs.getString("power"));
        return user;
    }
}
