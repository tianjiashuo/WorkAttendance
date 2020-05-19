package com.workattendance.Repository.dao;

/*外出*/

import com.workattendance.Repository.entity.GoOut;
import com.workattendance.Repository.mappers.GoOutRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class GoOutDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //添加记录
    public GoOut insert(GoOut goOut) {
        GoOut goOut1 = new GoOut();
        String sql = "INSERT INTO goOut (id,empNo,empName,startTime,endTime,reason,state,opinion) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, null, goOut.getEmpNo(),goOut.getEmpName(),goOut.getStartTime(),goOut.getEndTime(),goOut.getReason(),goOut.getState(),goOut.getOpinion());
        String sql2 = "SELECT * FROM goOut WHERE id=? ";
        goOut1= jdbcTemplate.queryForObject(sql2, new GoOutRowMapper(), goOut.getId());

        return goOut1;
    }

    //员工修改记录
    public GoOut updateGoOutByid(int id,GoOut goOut){
        String sql = "UPDATE goOut set startTime=? ,endTime=? ,reason=?  WHERE id=?";
        jdbcTemplate.update(sql, goOut.getStartTime(),goOut.getEndTime(),goOut.getReason(),id);
        return querygoOutById(id);
    }

    //根据员工编号删除记录
    public void deletegoOutById(int id) {
        String sql = "DELETE FROM goOut WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    //经理审核
    public GoOut updategoOutByPower(int id,GoOut goOut){
        String sql = "UPDATE goOut set state=?,opinion=?  WHERE id=?";
        jdbcTemplate.update(sql, goOut.getState(),goOut.getOpinion(),id);
        return querygoOutById(id);
    }

    //根据id查询
    public GoOut querygoOutById(int id) {
        String sql = "SELECT * FROM goOut WHERE id=?";
        GoOut goOut= jdbcTemplate.queryForObject(sql, new GoOutRowMapper(), id);
        return goOut;
    }

    //根据员工编号查询记录
    public List<GoOut> querygoOutByEmpNo(String empNo) {
        String sql = "SELECT * FROM goOut WHERE empNo=? ORDER BY id";
        List<GoOut> goOutList= jdbcTemplate.query(sql, new GoOutRowMapper(), empNo);
        return goOutList;
    }

    //返回所有员工记录
    public List<GoOut> querygoOut() {
        String sql = "SELECT * FROM goOut ORDER BY id";
        List<GoOut> goOutList= jdbcTemplate.query(sql, new GoOutRowMapper());
        return goOutList;
    }
}
