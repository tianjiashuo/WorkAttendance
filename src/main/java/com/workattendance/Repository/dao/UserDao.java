package com.workattendance.Repository.dao;

/*用户*/

import com.workattendance.Repository.entity.User;
import com.workattendance.Repository.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //根据员工编号得到员工信息
    public User queryUserByEmpNo(String empNo) {
        String sql = "SELECT * FROM user WHERE empNo=? ";
        User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), empNo);
        return user;
    }

}
