package com.workattendence.Service;

import com.workattendence.Repository.dao.GoOutDao;
import com.workattendence.Repository.entity.GoOut;

import java.util.List;

public class GoOutService {

    private GoOutDao goOutDao;

    //外出申请
    public GoOut inserGoOut(GoOut goOut){
        return goOutDao.insert(goOut);
    }

    //员工修改申请
    public GoOut updateGoOutById(int id, GoOut goOut){
        return goOutDao.updateGoOutByid(id,goOut);
    }

    //删除申请
    public void deleteGoOutById(int id){
        goOutDao.deletegoOutById(id);
    }

    //查询自己的外出记录
    public List<GoOut> queryGoOutByEmpNo (String empNo){
        return goOutDao.querygoOutByEmpNo(empNo);
    }

    //查询所有人的外出记录
    public List<GoOut> queryGoOut(){
        return goOutDao.querygoOut();
    }

    //审批申请
    public GoOut examineById(int id ,GoOut goOut){
        return goOutDao.updategoOutByPower(id,goOut);
    }
}
