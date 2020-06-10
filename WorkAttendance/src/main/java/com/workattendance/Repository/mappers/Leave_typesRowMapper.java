package com.workattendance.Repository.mappers;

import com.workattendance.Repository.entity.Leave_types;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Leave_typesRowMapper implements RowMapper<Leave_types> {

    @Override
    public Leave_types mapRow(ResultSet rs, int i) throws SQLException{
        Leave_types lt = new Leave_types();
        lt.setId(rs.getInt("id"));
        lt.setDays(rs.getInt("days"));
        lt.setLeaveName(rs.getString("leave_name"));
        //lt.setPaid
        return lt;
    }

}
