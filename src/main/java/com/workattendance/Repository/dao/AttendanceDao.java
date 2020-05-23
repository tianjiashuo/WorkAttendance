package com.workattendance.Repository.dao;

/*出勤*/

import com.workattendance.Repository.entity.Attendance;
import com.workattendance.Repository.mappers.AttendanceRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("attendanceDao")
public class AttendanceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    //得到单次记录
    public Attendance queryAttendanceById(int id) {
        String sql = "SELECT * FROM attendance WHERE id=? ";
        Attendance attendance= jdbcTemplate.queryForObject(sql, new AttendanceRowMapper(), id);
        return attendance;
    }

    /***
     * 查询某个员工的出勤纪录
     * @author mao
     * @param empNo
     * @return
     */
    public List<Attendance> queryAttendanceByEmpNo(String empNo) {
        String sql = "SELECT * FROM attendance WHERE emp_no=? ORDER BY time";
        List<Attendance> attendanceList= jdbcTemplate.query(sql, new AttendanceRowMapper(), empNo);
        return attendanceList;
    }

    /***
     * 返回所有员工的出勤纪录
     * @author
     * @param
     * @return
     */
    public List<Attendance> queryAllAttendance() {
        String sql = "SELECT * FROM attendance ORDER BY time";
        List<Attendance> attendanceList= jdbcTemplate.query(sql, new AttendanceRowMapper());
        return attendanceList;
    }

    /***
     * 按照日期查询全部人出勤纪录
     * @author mao
     * @param fromDate
     * @param endDate
     * @return
     */
    public List<Attendance> queryAllAttendanceByDate(String fromDate,String endDate) {
        String sql = "SELECT * FROM attendance WHERE time<=? AND  time>= ? ORDER BY emp_no";
        List<Attendance> attendanceList= jdbcTemplate.query(sql, new AttendanceRowMapper(),(endDate+ " 23:59:59"),(fromDate+" 00:00:00"));
        return attendanceList;
    }

}
