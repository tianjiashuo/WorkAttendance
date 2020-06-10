package com.workattendance.Service;

import com.workattendance.Repository.dao.GoOutDao;
import com.workattendance.Repository.dao.PowerDao;
import com.workattendance.Repository.dao.UserDao;
import com.workattendance.Repository.entity.GoOut;
import com.workattendance.Repository.entity.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.*;

@Service("goOutService")
public class GoOutService {

    @Autowired
    private GoOutDao goOutDao;
    private UserBo userBo = UserBo.getUserBo();
    @Autowired
    private PowerDao powerDao;

    /***
     * 外出申请
     * @author bai
     * @return
     */
    public GoOut inserGoOut(GoOut goOut){
        return goOutDao.insert(goOut);
    }

    /***
     * 员工修改申请
     * @author bai
     * @return
     */
    public GoOut updateGoOutById(int id, GoOut goOut){
        return goOutDao.updateGoOutByid(id, goOut);
    }

    /***
     * 删除申请
     * @author bai
     * @return
     */
    public void deleteGoOutById(int id){
        goOutDao.deletegoOutById(id);
    }

    /***
     * 查询自己的外出记录
     * @author bai
     * @return
     */
    public List<GoOut> queryGoOutByEmpNo (int empNo){
        return goOutDao.querygoOutByEmpNo(empNo);
    }

    /***
     * 查询一条外出记录详情
     * @author bai
     * @return
     */
    public GoOut queryGoOutById (int id){
        return goOutDao.querygoOutById(id);
    }

    /***
     * 查询所有人的外出记录
     * @author bai
     * @return
     */
    public List<GoOut> queryGoOut(){
        return goOutDao.querygoOut();
    }


    /*** shuo***/

    /***
     * 项目经理审批外出申请
     * @author shuo
     * @return
     */
    public void auditByDivision(int id ,GoOut response){
        if(userBo.getPower()!=0){
            if(powerDao.queryGooutApprovalPowerById(userBo.getPower())) {
                if(response.getState()){
                    goOutDao.updategoOutDivisionPass(id);
                }
                else{
                    goOutDao.updategoOutRefuse(id);
                }
            }else{
                System.out.println("您没有审核请假权限");
            }
        }
        else{
            System.out.println("请先登陆");
        }
    }

    /***
     * 副经理审批外出申请
     * @author shuo
     * @return
     */
    public void auditByVice(int id ,GoOut response){
        if(userBo.getPower()!=0){
            if(powerDao.queryGooutApprovalPowerById(userBo.getPower())) {
                GoOut goOut = goOutDao.querygoOutById(id);
                if(response.getState()){
                        if(convertTimeToLong(goOut.getEnd_time())-convertTimeToLong(goOut.getStart_time())>daytoSecond(3)){
                            goOutDao.updategoOutVicePass(id);
                            goOutDao.updategoOutPass(id);
                        }
                        else{
                            goOutDao.updategoOutVicePass(id);
                        }
                }
                else{
                    goOutDao.updategoOutRefuse(id);
                }
            }else{
                System.out.println("您没有审核请假权限");
            }
        }
        else{
            System.out.println("请先登陆");
        }
    }

    /***
     * 总经理审批外出申请
     * @author shuo
     * @return
     */
    public void auditByManager(int id ,GoOut response){
        if(userBo.getPower()!=0){
            if(powerDao.queryGooutApprovalPowerById(userBo.getPower())) {
                GoOut goOut = goOutDao.querygoOutById(id);
                if(response.getState()){
                    goOutDao.updategoOutManagerPass(id);
                    goOutDao.updategoOutPass(id);
                }
                else{
                    goOutDao.updategoOutRefuse(id);
                }
            }else{
                System.out.println("您没有审核请假权限");
            }
        }
        else{
            System.out.println("请先登陆");
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
