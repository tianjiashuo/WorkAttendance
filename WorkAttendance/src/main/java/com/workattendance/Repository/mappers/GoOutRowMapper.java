package com.workattendance.Repository.mappers;

import com.workattendance.Repository.entity.GoOut;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoOutRowMapper implements RowMapper<GoOut> {

    @Override
    public GoOut mapRow(ResultSet rs, int i) throws SQLException {
        GoOut goOut = new GoOut();
        goOut.setId(rs.getInt("id"));
        goOut.setEmp_no(rs.getInt("emp_no"));
        /*** mao***/
        goOut.setEmp_name(rs.getString("emp_name"));
        goOut.setStart_time(rs.getString("start_time"));
        goOut.setEnd_time(rs.getString("end_time"));
        goOut.setReason(rs.getString("reason"));
        goOut.setState(rs.getBoolean("state"));
        goOut.setDivision_manager_state(rs.getBoolean("division_manager_state"));
        goOut.setVice_manager_state(rs.getBoolean("vice_manager_state"));
        goOut.setManager_state(rs.getBoolean("manager_state"));
        return goOut;
    }
}
