package org.usersystem.demo.controller;


import org.springframework.web.bind.annotation.*;
import org.usersystem.demo.dao.OtherDao;
import org.usersystem.demo.opt.ResponseHelper;
import org.usersystem.demo.opt.ResponseV2;
import org.usersystem.demo.pojo.CliInfo;

import javax.annotation.Resource;
import java.util.List;
@RestController
@CrossOrigin
public class OtherControl {
    @Resource
    OtherDao otherDao;

    @RequestMapping(value = "/getCliInfo",method = RequestMethod.GET)
    @ResponseBody
    public ResponseV2 checkStaff(){
        try {
            List<CliInfo> list = otherDao.getCliInfo();
            System.out.println(list);
            return ResponseHelper.create(list, 200 , "科室信息列表查询成功");
        }catch (Exception e) {
            return ResponseHelper.create(500 , "查询失败");
        }
    }
}
