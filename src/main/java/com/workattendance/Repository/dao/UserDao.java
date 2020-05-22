package com.workattendance.Repository.dao;

/*用户*/

import com.workattendance.Repository.entity.GoOut;
import com.workattendance.Repository.entity.Leave;
import com.workattendance.Repository.entity.User;
import com.workattendance.Repository.mappers.GoOutRowMapper;
import com.workattendance.Repository.mappers.LeaveRowMapper;
import com.workattendance.Repository.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //根据员工编号得到员工信息
    public User queryUserByEmpNo(String empNo) {
        String sql = "SELECT * FROM user WHERE empNo=? ";
        User user = jdbcTemplate.queryForObject(sql, new UserRowMapper(), empNo);
        return user;
    }

    /***
     * 查询所有状态为"外出"的员工相关纪录
     * @author mao
     * @param date
     * @return
     */
    public List<GoOut> getAllGoOutState(String date){
        String sql = "SELECT * FROM goout WHERE emp_id IN(SELECT emp_id FROM user where state=?) AND start_time < ? AND state =?";
        List<GoOut> goOutStateIfm= jdbcTemplate.query(sql,new GoOutRowMapper(),"外出",date,1);
        return goOutStateIfm;
    }

    /***
     * 查询所有状态为 “带薪休假”的员工纪录
     * @author mao
     * @param date
     * @return
     */
    public List<Leave> getAllPaidLeaveState(String date){
        String sql = "SELECT * FROM leave_request WHERE emp_id IN(SELECT emp_id FROM user where state=?) AND start_time < ? AND state= ?";
        List<Leave> paidLeaveStateIfm = jdbcTemplate.query(sql,new LeaveRowMapper(),"带薪休假",date,1);
        return paidLeaveStateIfm;
    }

    /***
     * 查询所有状态为“无薪休假"的员工纪录
     * @author mao
     * @param date
     * @return
     */
    public List<Leave> getAllUnpaidLeaveState(String date){
        String sql = "SELECT * FROM leave_request WHERE emp_id IN(SELECT emp_id FROM user where state=?) AND start_time < ? AND state= ?";
        List<Leave> unpaidLeaveStateIfm = jdbcTemplate.query(sql,new LeaveRowMapper(),"无薪休假",date,1);
        return unpaidLeaveStateIfm;
    }

    /***
     * 查询所有状态为”在公司的员工“
     * @author mao
     * @param date
     * @return
     */
    public List<User> getAllInCompanyState(String date){
        String sql = "SELECT * FROM user WHERE state = ?";
        List<User> inCompanyUser=jdbcTemplate.query(sql,new UserRowMapper(),"在公司");
        return inCompanyUser;
    }


}
