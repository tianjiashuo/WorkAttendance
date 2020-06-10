package com.workattendance.Repository.dao;

import com.workattendance.Repository.entity.Check;
import com.workattendance.Repository.mappers.CheckRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("checkDao")
public class CheckDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    /***
     * 更新check表所需功能
     * @author shuo
     * @return
     */

    //增加考勤记录
    public void updateCheck(Check check){
        String sql = "INSERT INTO checktable (emp_no,emp_name,leave_days,absenteeism_days,late_days,leave_early_days,date) VALUES(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,check.getEmpNo(),check.getEmpName(),check.getLeaveDays(),check.getAbsenteeismDays(),
                check.getLateDays(),check.getLeaveEarlyDays(),check.getDate());
    }

    //根据empNo和date查询记录
    public Check queryCheckByEmpNo(String empNo,String date) {
        String sql = "SELECT * FROM checktable WHERE emp_no=? AND date LIKE ?";
        Check check = jdbcTemplate.queryForObject(sql, new CheckRowMapper(), empNo,date);
        return check;
    }

    /***
     * 查看某个时段的check
     * @author mao
     * @param fromDate
     * @param endDate
     * @return
     */
    public List<Check> queryAllCheckByDay(String fromDate,String endDate){
        String sql = "SELECT * FROM checktable WHERE date> ?AND date<=? ";
        List<Check> checks = jdbcTemplate.query(sql,new CheckRowMapper(),fromDate+" 00:00:00",endDate+" 23:59:59");
        return checks;
    }

}
