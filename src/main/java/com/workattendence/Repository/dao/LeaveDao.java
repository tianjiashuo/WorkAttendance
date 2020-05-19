package com.workattendence.Repository.dao;

/*请假*/

import com.workattendence.Repository.entity.Leave;
import com.workattendence.Repository.mappers.LeaveRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class LeaveDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //添加记录
    public Leave insert(Leave leave) {
        Leave leave1 = new Leave();
        String sql = "INSERT INTO leave (id,empNo,empName,type,startTime,endTime,reason,state,opinion) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, null, leave.getEmpNo(),leave.getEmpName(),leave.getType(),leave.getStartTime(),leave.getEndTime(),leave.getReason(),leave.getState(),leave.getOpinion());
        String sql2 = "SELECT * FROM leave WHERE id=? ";
        leave1= jdbcTemplate.queryForObject(sql2, new LeaveRowMapper(), leave.getId());

        return leave1;
    }

    //员工修改记录
    public Leave updateLeaveByid(int id,Leave leave){
        String sql = "UPDATE leave set type =? ,startTime=? ,endTime=? ,reason=?  WHERE id=?";
        jdbcTemplate.update(sql, leave.getType(),leave.getStartTime(),leave.getEndTime(),leave.getReason(),id);
        return queryLeaveById(id);
    }

    //根据员工编号删除记录
    public void deleteLeaveById(int id) {
        String sql = "DELETE FROM leave WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    //经理审核
    public Leave updateLeaveByPower(int id,Leave leave){
        String sql = "UPDATE leave set state=?,opinion=?  WHERE id=?";
        jdbcTemplate.update(sql, leave.getState(),leave.getOpinion(),id);
        return queryLeaveById(id);
    }

    //根据id查询
    public Leave queryLeaveById(int id) {
        String sql = "SELECT * FROM leave WHERE id=?";
        Leave leave= jdbcTemplate.queryForObject(sql, new LeaveRowMapper(), id);
        return leave;
    }

    //根据员工编号查询记录
    public List<Leave> queryLeaveByEmpNo(String empNo) {
        String sql = "SELECT * FROM leave WHERE empNo=? ORDER BY id";
        List<Leave> leaveList= jdbcTemplate.query(sql, new LeaveRowMapper(), empNo);
        return leaveList;
    }

    //返回所有员工记录
    public List<Leave> queryLeave() {
        String sql = "SELECT * FROM leave ORDER BY id";
        List<Leave> LeaveList= jdbcTemplate.query(sql, new LeaveRowMapper());
        return LeaveList;
    }

}
