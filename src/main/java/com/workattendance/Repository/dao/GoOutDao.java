package com.workattendance.Repository.dao;

/*外出*/

import com.workattendance.Repository.entity.GoOut;
import com.workattendance.Repository.entity.Leave;
import com.workattendance.Repository.mappers.GoOutRowMapper;
import com.workattendance.Repository.mappers.LeaveRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository("goOutDao")
public class GoOutDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //添加记录
    public GoOut insert(GoOut goOut) {
        GoOut goOut1 = new GoOut();
        String sql = "INSERT INTO goout (emp_no,start_time,end_time,reason,state,division_manager_state,vice_manager_state,manager_state,emp_name) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, goOut.getEmp_no(),goOut.getStart_time(),goOut.getEnd_time(),goOut.getReason(),goOut.getState(),goOut.getDivision_manager_state(),goOut.getVice_manager_state(),goOut.getManager_state(),goOut.getEmp_name());
        String sql2 = "SELECT * FROM goOut WHERE emp_no=? AND start_time=?";
        goOut1= jdbcTemplate.queryForObject(sql2, new GoOutRowMapper(),goOut.getEmp_no(),goOut.getStart_time());
        return goOut1;
    }

    //员工修改记录
    public GoOut updateGoOutByid(int id, GoOut goOut){
        String sql = "UPDATE goout set start_time=? ,end_time=? ,reason=?  WHERE id=?";
        jdbcTemplate.update(sql, goOut.getStart_time(),goOut.getEnd_time(),goOut.getReason(),id);
        return querygoOutById(id);
    }

    //根据id删除记录
    public void deletegoOutById(int id) {
        String sql = "DELETE FROM goout WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    //根据id查询
    public GoOut querygoOutById(int id) {
        String sql = "SELECT * FROM goout WHERE id=?";
        GoOut goOut= jdbcTemplate.queryForObject(sql, new GoOutRowMapper(), id);
        return goOut;
    }

    //根据员工编号查询记录
    public List<GoOut> querygoOutByEmpNo(int emp_no) {
        String sql = "SELECT * FROM goout WHERE emp_no=? ORDER BY id";
        List<GoOut> goOutList= jdbcTemplate.query(sql, new GoOutRowMapper(), emp_no);
        return goOutList;
    }

    //返回所有员工记录
    public List<GoOut> querygoOut() {
        String sql = "SELECT * FROM goout ORDER BY id";
        List<GoOut> goOutList= jdbcTemplate.query(sql, new GoOutRowMapper());
        return goOutList;
    }


    //审核更新goout表
    //部门经理审核外出通过
    public void updategoOutDivisionPass(int id){
        String sql = "UPDATE goOut set division_manager_state=1 WHERE id=?";
        jdbcTemplate.update(sql,id);
    }

    //副经理审核外出通过
    public void updategoOutVicePass(int id){
        String sql = "UPDATE goOut set vice_manager_state=1 WHERE id=?";
        jdbcTemplate.update(sql,id);
    }
    //总经理审核外出通过
    public void updategoOutManagerPass(int id){
        String sql = "UPDATE goOut set manager_state=1 WHERE id=?";
        jdbcTemplate.update(sql,id);
    }
    //外出审核通过
    public void updategoOutPass(int id){
        String sql = "UPDATE goOut set state=1 WHERE id=?";
        jdbcTemplate.update(sql,id);
//        return querygoOutById(id);
    }
    //审核外出拒绝
    public void updategoOutRefuse(int id){
        String sql = "UPDATE goOut set state=2 WHERE id=?";
        jdbcTemplate.update(sql,id);
    }
    /***
     * 查询所有员工的外出情况
     * @param fromDate
     * @param endDate
     * @return
     */
    public List<GoOut> queryAllGoOut(String fromDate, String endDate) {
        String sql = "SELECT * FROM goout WHERE start_time< ? AND end_time >= ? AND state = 1 " +
                "UNION SELECT * FROM  goout WHERE  start_time > ? AND start_time <= ? AND state =1  " +
                "ORDER BY id asc";

        List<GoOut> gooutList= jdbcTemplate.query(sql, new GoOutRowMapper(),fromDate,fromDate,fromDate,endDate);
        return gooutList;
    }
}
