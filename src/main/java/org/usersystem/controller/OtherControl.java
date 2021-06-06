package org.usersystem.controller;


import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.usersystem.dao.OtherDao;
import org.usersystem.opt.ResponseHelper;
import org.usersystem.opt.ResponseV2;
import org.usersystem.pojo.CliInfo;
import org.usersystem.pojo.StaffInfo;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "其他操作接口")
@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class OtherControl {
    @Resource
    OtherDao otherDao;

    @ApiOperation("获取科室信息")
    @RequestMapping(value = "/getCliInfo", method = RequestMethod.GET)
    public ResponseV2 getCliInfo() {
        try {
            List<CliInfo> list = otherDao.getCliInfo();
            System.out.println(list);
            return ResponseHelper.create(list, 200, "科室信息列表查询成功");
        } catch (Exception e) {
            return ResponseHelper.create(500, "查询失败");
        }
    }


    @ApiOperation("获取医生信息")
    @RequestMapping(value = "/getStaffInfo", method = RequestMethod.POST)
    public ResponseV2 getStaffInfo(@RequestBody JSONObject jsonObject) {
        String id = jsonObject.getString("id");
        try {
            List<StaffInfo> list = otherDao.getStaffInfo(id);
            System.out.println(list);
            return ResponseHelper.create(list, 200, "科室信息列表查询成功");
        } catch (Exception e) {
            return ResponseHelper.create(500, "查询失败");
        }
    }

    @ApiOperation("获取医生信息")
    @GetMapping("/getStaffInfoList")
    public ResponseV2 getStaffInfoList() {
        return ResponseHelper.create(otherDao.getStaffInfoList());
    }
}
