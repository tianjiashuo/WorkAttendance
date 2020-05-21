package com.workattendance.Repository.mappers;

import com.workattendance.Repository.entity.Leave;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LeaveRowMapper implements RowMapper<Leave> {

    @Override
    public Leave mapRow(ResultSet rs, int i) throws SQLException {
        Leave leave = new Leave();
        leave.setId(rs.getInt("id"));
        leave.setEmp_no(rs.getInt("emp_no"));
        /***mao***/
        leave.setType(rs.getInt("type"));
        leave.setStart_time(rs.getString("start_time"));
        leave.setEnd_time(rs.getString("end_time"));
        leave.setReason(rs.getString("reason"));
        leave.setState(rs.getBoolean("state"));
        leave.setDivision_manager_state(rs.getBoolean("division_manager_state"));
        leave.setVice_manager_state(rs.getBoolean("vice_manager_state"));
        leave.setManager_state(rs.getBoolean("manager_state"));
        return leave;
    }
}
