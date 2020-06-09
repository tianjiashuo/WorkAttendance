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
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //根据员工编号得到员工信息
    public User queryUserByEmpNo(String empNo) {
        String sql = "SELECT * FROM user WHERE emp_no=? ";
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
        String sql = "SELECT * FROM goout WHERE emp_no IN (SELECT emp_no FROM user where state=?) AND start_time < ? AND state =?";
        List<GoOut> goOutStateIfm= jdbcTemplate.query(sql,new GoOutRowMapper(),"外出",date,1);
        return goOutStateIfm;
    }

    /***
     * 查询所有状态为 “带薪休假”、“无薪休假"的员工纪录
     * @author mao
     * @param date
     * @return
     */
    public List<Leave> getAllLeaveState(String date,String state){
        String sql = "SELECT * FROM leave_request WHERE emp_no IN(SELECT emp_no FROM user where state=?) AND start_time < ? AND state= ?";
        List<Leave> paidLeaveStateIfm = jdbcTemplate.query(sql,new LeaveRowMapper(),state,date,1);
        return paidLeaveStateIfm;
    }

//    /***
//     * 查询所有状态为“无薪休假"的员工纪录
//     * @author mao
//     * @param date
//     * @return
//     */
//    public List<Leave> getAllUnpaidLeaveState(String date){
//        String sql = "SELECT * FROM leave_request WHERE emp_id IN(SELECT emp_id FROM user where state=?) AND start_time < ? AND state= ?";
//        List<Leave> unpaidLeaveStateIfm = jdbcTemplate.query(sql,new LeaveRowMapper(),"无薪休假",date,1);
//        return unpaidLeaveStateIfm;
//    }

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

    /***
     * 查询所有员工
     * @author shuo
     * @return
     */
    public List<User> getAllUser(){
        String sql = "SELECT * FROM user";
        List<User> User=jdbcTemplate.query(sql,new UserRowMapper());
        return User;
    }

    /***
     * 更新员工状态
     * @author shuo
     * @return
     */
    public void updateUserStateByEmpNo(int empNo,String state){
        String sql = "UPDATE user set state=? WHERE emp_no=?";
        jdbcTemplate.update(sql,state,empNo);
    }

    /***
     * 员工修改密码
     * @author bai
     * @return
     */
    public Boolean updatePasswordByEmpNo(String empNo, String oldPassword,String newPassword){
        boolean isChange = false;
        User user = queryUserByEmpNo(empNo);
        String password = user.getPassword().hashCode() + "";
        //说明密码正确
        if( oldPassword.equals(password)){
            String sql2 = "UPDATE user set password=? WHERE emp_no=?";
            jdbcTemplate.update(sql2, newPassword,empNo);
        }
        User newUser = queryUserByEmpNo(empNo);
        if(newUser.getPassword().equals(newPassword)){
            isChange = true;
        }
        return isChange;
    }



}
