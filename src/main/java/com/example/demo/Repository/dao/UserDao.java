package com.example.demo.Repository.dao;

/*用户*/

import com.example.demo.Repository.entity.Leave;
import com.example.demo.Repository.mappers.UserRowMapper;
import com.example.demo.Repository.entity.User;
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
