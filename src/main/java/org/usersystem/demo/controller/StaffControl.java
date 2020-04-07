package org.usersystem.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.usersystem.demo.opt.ResponseHelper;
import org.usersystem.demo.opt.ResponseV2;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class StaffControl {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 checkStaff(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject.toJSONString());
        Map<String,String> res = new HashMap<>();
        res.put("user_id","123");
        res.put("user_type","1");
        return ResponseHelper.create(res,200,"请求成功");
    }
}
