package com.workattendance.Service;

import com.workattendance.Repository.dao.PowerDao;
import com.workattendance.Repository.entity.Power;

public class PowerService {

    private PowerDao powerDao;

    //得到等级权限说明
    public Power getPower(String power){
       return powerDao.queryPowerByEmpPower(power);
    }
}
