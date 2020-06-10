package com.workattendance.Repository.dao;

/*请假*/

import com.workattendance.Repository.entity.Leave;
import com.workattendance.Repository.entity.Leave_balances;
import com.workattendance.Repository.entity.Leave_types;
import com.workattendance.Repository.entity.User;
import com.workattendance.Repository.mappers.LeaveRowMapper;
import com.workattendance.Repository.mappers.Leave_balancesRowMapper;
import com.workattendance.Repository.mappers.Leave_typesRowMapper;
import com.workattendance.Repository.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("leaveDao")
public class LeaveDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /***
     * 添加记录
     * @author bai
     * @return
     */
    public Leave insert(Leave leave) {
        Leave leave1 = new Leave();
        String sql2 = "INSERT INTO leave_request (emp_no,emp_name,type,start_time,end_time,reason,state,division_manager_state,vice_manager_state,manager_state) VALUES(?, ?, ? ,?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql2, leave.getEmp_no(),leave.getEmp_name(),leave.getType(),leave.getStart_time(),leave.getEnd_time(),leave.getReason(),leave.getState(),leave.getDivision_manager_state(),leave.getVice_manager_state(),leave.getManager_state());
        String sql3 = "SELECT * FROM leave_request WHERE emp_no=? AND start_time=?";
        leave1= jdbcTemplate.queryForObject(sql3, new LeaveRowMapper(), leave.getEmp_no(),leave.getStart_time());
        return leave1;
    }

    /***
     * 员工修改记录
     * @author bai
     * @return
     */
    public Leave updateLeaveByid(int id,Leave leave){
        String sql = "UPDATE leave_request set type =? ,start_time=? ,end_time=? ,reason=? ,state=? ,division_manager_state=? ,vice_manager_state=? ,manager_state=?  WHERE id=?";
        jdbcTemplate.update(sql, leave.getType(),leave.getStart_time(),leave.getEnd_time(),leave.getReason(),false,false,false,false,id);
        return queryLeaveById(id);
    }

    /***
     * 根据id删除记录
     * @author bai
     * @return
     */
    public void deleteLeaveById(int id) {
        String sql = "DELETE FROM leave_request WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    /***
     * 根据id查询
     * @author bai
     * @return
     */
    public Leave queryLeaveById(int id) {
        String sql = "SELECT * FROM leave_request WHERE id=?";
        Leave leave= jdbcTemplate.queryForObject(sql, new LeaveRowMapper(), id);
        return leave;
    }

    /***
     * 根据员工编号查询记录
     * @author bai
     * @return
     */
    public List<Leave> queryLeaveByEmpNo(int emp_no) {
        String sql = "SELECT * FROM leave_request WHERE emp_no=? ORDER BY id";
        List<Leave> leaveList= jdbcTemplate.query(sql, new LeaveRowMapper(), emp_no);
        return leaveList;
    }


    /***
     * 查询所有员工的请假情况
     * @param fromDate
     * @param endDate
     * @return
     */
    public List<Leave> queryAllLeave(String fromDate,String endDate) {
        String sql = "SELECT * FROM leave_request WHERE start_time< ? AND end_time >= ? AND state = 1 " +
                "UNION SELECT * FROM  leave_request WHERE  start_time > ? AND start_time <= ? AND state =1  " +
                "ORDER BY id asc";

        List<Leave> LeaveList= jdbcTemplate.query(sql, new LeaveRowMapper(),fromDate,fromDate,fromDate,endDate);
        return LeaveList;
    }

    //获得假期是否带薪
    public boolean queryLeaveSalary(int leaveId) {
        String sql = "SELECT `is_salary` FROM leave_types WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{leaveId}, Boolean.class);
    }

    //查找剩余假期数
    public int queryLeaveBalance(int empNo,int leaveId){
        String sql = "SELECT `balances` FROM leave_balances WHERE emp_no=? AND leave_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{empNo,leaveId}, Integer.class);
    }

    //审核通过后修改假期余额
    public void updateLeaveBalance(int empNo,int leaveId,Long balances){
        String sql = "UPDATE leave_balances set balances=? WHERE emp_no=? AND leave_id = ?";
        jdbcTemplate.update(sql,balances,empNo,leaveId);
    }

    //审核更新leave_request表
    //部门经理审核外出通过
    public void updateLeaveDivisionPass(int id){
        String sql = "UPDATE leave_request set division_manager_state=1 WHERE id=?";
        jdbcTemplate.update(sql,id);
    }

    //副经理审核外出通过
    public void updateLeaveVicePass(int id){
        String sql = "UPDATE leave_request set vice_manager_state=1 WHERE id=?";
        jdbcTemplate.update(sql,id);
    }
    //总经理审核外出通过
    public void updateLeaveManagerPass(int id){
        String sql = "UPDATE leave_request set manager_state=1 WHERE id=?";
        jdbcTemplate.update(sql,id);
    }
    //请假条审核通过
    public void updateLeavePass(int id){
        String sql = "UPDATE leave_request set state=1 WHERE id=?";
        jdbcTemplate.update(sql,id);
    }
    //审核外出拒绝
    public void updateLeaveRefuse(int id){
        String sql = "UPDATE leave_request set state=2 WHERE id=?";
        jdbcTemplate.update(sql,id);
    }

    //设置可休假期
    public void setLeaveBalances(Leave_balances lb){
        String sql = "INSERT INTO leave_balances (emp_no,leave_id,days,balances) VALUES(?, ?, ?, ?)";
        jdbcTemplate.update(sql,lb.getEmpNo(),lb.getLeaveId(),lb.getDays(),lb.getBalances());
    }


    public Leave_balances queryLBalancesByempNoAndLd(int empNo,int leaveId){
        String sql = "SELECT * FROM leave_balances WHERE emp_no=? AND leave_id=?";
        Leave_balances lb = jdbcTemplate.queryForObject(sql,new Leave_balancesRowMapper(),empNo,leaveId);
        return lb;
    }

    //员工此前设置过年假 更新
    public void updateExistLeaveBalances(int emp_no,int days){
        String sql = "UPDATE leave_balances set days=?,balances=? WHERE emp_no=? AND leave_id=4";
        jdbcTemplate.update(sql,days,days,emp_no);
    }

    //根据工龄筛选员工
    public List<User> queryEmpNoByYears(int lowYear, int highYear){
        String sql = "SELECT * FROM user WHERE years >=? AND years<=?";
        List<User> users = jdbcTemplate.query(sql, new UserRowMapper(), lowYear, highYear);
        return users;
    }

    //查询假期id
    public List<Leave_types> queryLeaveType(int id){
        String sql = "SELECT * FROM leave_types WHERE id=?";
        List<Leave_types> lt= jdbcTemplate.query(sql, new Leave_typesRowMapper(),id);
        return lt;
    }

}
