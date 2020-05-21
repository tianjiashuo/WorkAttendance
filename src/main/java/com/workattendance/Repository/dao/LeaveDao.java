package com.workattendance.Repository.dao;

/*请假*/

import com.workattendance.Repository.entity.GoOut;
import com.workattendance.Repository.entity.Leave;
import com.workattendance.Repository.mappers.GoOutRowMapper;
import com.workattendance.Repository.mappers.LeaveRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //添加记录
    public Leave insert(Leave leave) {
        Leave leave1 = new Leave();
        String sql2 = "INSERT INTO askforleave (emp_no,type,start_time,end_time,reason,state,division_manager_state,vice_manager_state,manager_state) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql2, leave.getEmp_no(),leave.getType(),leave.getStart_time(),leave.getEnd_time(),leave.getReason(),leave.getState(),leave.getDivision_manager_state(),leave.getVice_manager_state(),leave.getManager_state());
        String sql3 = "SELECT * FROM askforleave WHERE emp_no=? AND start_time=?";
        leave1= jdbcTemplate.queryForObject(sql3, new LeaveRowMapper(), leave.getEmp_no(),leave.getStart_time());
        return leave1;
    }

    //员工修改记录
    public Leave updateLeaveByid(int id,Leave leave){
        String sql = "UPDATE askforleave set type =? ,start_time=? ,end_time=? ,reason=?  WHERE id=?";
        jdbcTemplate.update(sql, leave.getType(),leave.getStart_time(),leave.getEnd_time(),leave.getReason(),id);
        return queryLeaveById(id);
    }

    //根据员工编号删除记录
    public void deleteLeaveById(int id) {
        String sql = "DELETE FROM askforleave WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

//    //经理审核
//    public Leave updateLeaveByPower(int id,Leave leave){
//        String sql = "UPDATE leave set state=?,opinion=?  WHERE id=?";
//        jdbcTemplate.update(sql, leave.getState(),leave.getOpinion(),id);
//        return queryLeaveById(id);
//    }

    //根据id查询
    public Leave queryLeaveById(int id) {
        String sql = "SELECT * FROM askforleave WHERE id=?";
        Leave leave= jdbcTemplate.queryForObject(sql, new LeaveRowMapper(), id);
        return leave;
    }

    //根据员工编号查询记录
    public List<Leave> queryLeaveByEmpNo(int emp_no) {
        String sql = "SELECT * FROM askforleave WHERE emp_no=? ORDER BY id";
        List<Leave> leaveList= jdbcTemplate.query(sql, new LeaveRowMapper(), emp_no);
        return leaveList;
    }

    //返回所有员工记录
    public List<Leave> queryLeave() {
        String sql = "SELECT * FROM askforleave ORDER BY id";
        List<Leave> LeaveList= jdbcTemplate.query(sql, new LeaveRowMapper());
        return LeaveList;
    }

}