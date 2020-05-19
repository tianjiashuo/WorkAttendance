package com.example.demo.Service;

import com.example.demo.Repository.dao.PowerDao;
import com.example.demo.Repository.entity.Power;

public class PowerService {

    private PowerDao powerDao;

    //得到等级权限说明
    public Power getPower(String power){
       return powerDao.queryPowerByEmpPower(power);
    }
}
