package org.usersystem.demo.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.usersystem.demo.dao.OtherDao;
import org.usersystem.demo.opt.ResponseHelper;
import org.usersystem.demo.opt.ResponseV2;
import org.usersystem.demo.pojo.CliInfo;
import org.usersystem.demo.pojo.StaffInfo;

import javax.annotation.Resource;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class OtherControl {
    @Resource
    OtherDao otherDao;

    //获取科室信息
    @RequestMapping(value = "/getCliInfo",method = RequestMethod.GET)
    @ResponseBody
    public ResponseV2 getCliInfo(){
        try {
            List<CliInfo> list = otherDao.getCliInfo();
            System.out.println(list);
            return ResponseHelper.create(list, 200 , "科室信息列表查询成功");
        }catch (Exception e) {
            return ResponseHelper.create(500 , "查询失败");
        }
    }


    //获取医生信息
    @RequestMapping(value = "/getStaffInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 getStaffInfo(@RequestBody JSONObject jsonObject){
        String id = jsonObject.getString("id");
        try {
            List<StaffInfo> list = otherDao.getStaffInfo(id);
            System.out.println(list);
            return ResponseHelper.create(list, 200 , "科室信息列表查询成功");
        }catch (Exception e) {
            return ResponseHelper.create(500 , "查询失败");
        }
    }
}
