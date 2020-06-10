package com.workattendance.Repository.mappers;
import com.workattendance.Repository.entity.Leave_balances;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Leave_balancesRowMapper implements RowMapper<Leave_balances>{
    @Override
    public Leave_balances mapRow(ResultSet rs, int i) throws SQLException{
        Leave_balances lb = new Leave_balances();
        lb.setBalances(rs.getInt("balances"));
        lb.setDays(rs.getInt("days"));
        lb.setEmpNo(rs.getInt("emp_no"));
        lb.setLeaveId(rs.getInt("leave_id"));
        return lb;
    }
}

