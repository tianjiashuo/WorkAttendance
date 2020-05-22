package com.workattendance.Service;

import com.workattendance.Repository.dao.GoOutDao;
import com.workattendance.Repository.entity.GoOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.*;

@Service
public class GoOutService {

    @Autowired
    private GoOutDao goOutDao;

    //ceshi
    public String test(){
        return "test";
    }

    //外出申请
    public GoOut inserGoOut(GoOut goOut){
        return goOutDao.insert(goOut);
    }

    //员工修改申请
    public GoOut updateGoOutById(int id, GoOut goOut){
        return goOutDao.updateGoOutByid(id, goOut);
    }

    //删除申请
    public void deleteGoOutById(int id){
        goOutDao.deletegoOutById(id);
    }

    //查询自己的外出记录
    public List<GoOut> queryGoOutByEmpNo (int empNo){
        return goOutDao.querygoOutByEmpNo(empNo);
    }

    //查询所有人的外出记录
    public List<GoOut> queryGoOut(){
        return goOutDao.querygoOut();
    }
    /*** shuo***/
    //项目经理审批外出申请
    public GoOut auditByDivision(int id ,GoOut goOut,boolean response){
        if(response == true){
            return goOutDao.updategoOutDivisionPass(id,goOut);
        }
        else{
            return goOutDao.updategoOutRefuse(id,goOut);
        }
    }

    //副经理审批外出申请
    public GoOut auditByVice(int id ,GoOut goOut,boolean response){
        if(response == true){
                if(convertTimeToLong(goOut.getEnd_time())-convertTimeToLong(goOut.getStart_time())>daytoSecond(3)){
                    goOutDao.updategoOutVicePass(id,goOut);
                    return goOutDao.updategoOutPass(id,goOut);
                }
                else{
                    return goOutDao.updategoOutVicePass(id,goOut);
                }
        }
        else{
            return goOutDao.updategoOutRefuse(id,goOut);
        }
    }

    //总经理审批外出申请
    public GoOut auditByManager(int id ,GoOut goOut,boolean response){
        if(response == true){
            goOutDao.updategoOutManagerPass(id,goOut);
            return goOutDao.updategoOutPass(id,goOut);
        }
        else{
            return goOutDao.updategoOutRefuse(id,goOut);
        }
    }

    public static Long convertTimeToLong(String time) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parse = LocalDateTime.parse(time, ftf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()/1000;
    }

    public static Long daytoSecond(int days){
        long oneDaySeconds = 86400;
        return days*oneDaySeconds;
    }
}
