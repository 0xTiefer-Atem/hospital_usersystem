package org.usersystem.demo.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.usersystem.demo.dao.BlackListDao;
import org.usersystem.demo.dao.RegisterDao;
import org.usersystem.demo.dao.ReserveDao;
import org.usersystem.demo.opt.*;
import org.usersystem.demo.pojo.BlackListInfo;
import org.usersystem.demo.pojo.RegisterHistoryInfo;
import org.usersystem.demo.pojo.RegisterInfo;
import org.usersystem.demo.pojo.ReserveInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class RegisterControl {

    @Autowired
    ReserveDao reserveDao;

    @Autowired
    BlackListDao blackListDao;

    @Autowired
    RegisterDao registerDao;

    //添加登记信息
    @RequestMapping("/register/add")
    @ResponseBody
    public ResponseV2 addRegisterInfo(@RequestBody JSONObject para){
        String reserve_id = para.getString("reserve_id");
        ReserveInfo reserveInfo = reserveDao.reserveInfoDetail(reserve_id);
        if(reserveInfo == null){
            return ResponseHelper.create(null,1002,"未找到该预约信息");
        }

        String current_time = TimeOpt.getCurrentTime();
        String reserve_time = reserveInfo.getReserve_time();

        int flag = TimeOpt.is_late(current_time,reserve_time);
        if(flag == -1){
            String user_id = reserveInfo.getUser_id();
            BlackListInfo blackListInfo;
            blackListInfo = blackListDao.searchBalckUserById(user_id);
            if(blackListInfo != null){
                String end_time = blackListInfo.getEnd_time();
                return ResponseHelper.create(null,1004,"未在预约之前进行挂号登记!已记录进黑名单,还未解禁,一周内不能再次预约挂号!解禁时间: "+end_time);
            }
            String start_time = TimeOpt.getCurrentTime();
            String end_time = TimeOpt.getFetureDate(7);
            blackListInfo.setUser_id(user_id);
            blackListInfo.setStart_time(start_time);
            blackListInfo.setEnd_time(end_time);
            blackListDao.insterBlackUser(blackListInfo);
            return ResponseHelper.create(null,1002,"未在预约之前进行挂号登记!已记录进黑名单并且一周内不能再次预约挂号!");
        }else if(flag == 1003){
            return ResponseHelper.create(null,1003,"挂号失败!请稍候再试");
        }

        if(registerDao.searchReserveById(reserve_id) >=1){
            return ResponseHelper.create(null,1005,"该预约已经进行登记挂号,请前去相应科室等待");
        }

        current_time = TimeOpt.getCurrentTime();

        Map<String,String> resultMap = new HashMap<>();
        RegisterInfo registerInfo = new RegisterInfo();
        String register_id = GetUUID.getUUID();
        registerInfo.setRegister_id(register_id);
        registerInfo.setReserve_id(reserve_id);
        registerInfo.setRegister_time(current_time);
        registerInfo.setWait_status("-1");//正在排队
        registerInfo.setRegister_status("1010");//1010已预约且在预约时间之前登记
        registerDao.addRegisterInfo(registerInfo);
        resultMap.put("register_id",register_id);
        return ResponseHelper.create(resultMap,200,"登记挂号成功!请前往对应科室等待");
    }

    //更新登录状态
    @RequestMapping("/register/done")
    @ResponseBody
    public ResponseV2 registerCompleted(@RequestBody JSONObject para){
        String register_id = para.getString("register_id");
        registerDao.updateRegisterInfo(register_id);
        return ResponseHelper.create(null,200,"登记信息更新完成");
    }

    //查看一段时间登记挂号记录
    @RequestMapping("/register/history")
    @ResponseBody
    public String getRegisterHistory(@RequestParam Map<String,Object> para){
        HashMap hashMap = new HashMap();
        List<RegisterHistoryInfo> historyInfoList = registerDao.searchRegisterHistory(hashMap);
        JSONObject jobj = new JSONObject();
        jobj.put("code",0);
        jobj.put("msg","");
        Map<String,List> para1 = new HashMap<>();
        para1.put("datas",historyInfoList);
        jobj.put("data",para1);
        System.out.println(jobj.toString());
        return jobj.toString();
    }
}