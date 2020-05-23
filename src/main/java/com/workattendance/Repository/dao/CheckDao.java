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

    //更新年假
    public Check updateAnnualLeaveByEmpNo(String empNo, int annualLeave){
        String sql = "UPDATE checktable set annualLeave=?  WHERE empNo=?";
        jdbcTemplate.update(sql, annualLeave,empNo);
        return queryCheckByEmpNo(empNo);
    }

    //更新请假次数
    public Check updateLeaveTimeByEmpNo(String empNo, int leaveTime){
        String sql = "UPDATE checktable set leaveTime=?  WHERE empNo=?";
        jdbcTemplate.update(sql, leaveTime,empNo);
        return queryCheckByEmpNo(empNo);
    }

    //更新缺勤次数
    public Check updateAbsenteeismByEmpNo(String empNo, int absenteeism){
        String sql = "UPDATE checktable set absenteeism=?  WHERE empNo=?";
        jdbcTemplate.update(sql, absenteeism,empNo);
        return queryCheckByEmpNo(empNo);
    }

    //根据empNo查询
    public Check queryCheckByEmpNo(String empNo) {
        String sql = "SELECT * FROM checktable WHERE empNo=?";
        Check check = jdbcTemplate.queryForObject(sql, new CheckRowMapper(), empNo);
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
