package com.workattendance.Service;

import com.workattendance.Repository.dao.GoOutDao;
import com.workattendance.Repository.entity.GoOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("goOutService")
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

//    //审批申请
//    public GoOut examineById(int id ,GoOut goOut){
//        return goOutDao.updategoOutByPower(id,goOut);
//    }
}
