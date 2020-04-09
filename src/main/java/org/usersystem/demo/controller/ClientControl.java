package org.usersystem.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.usersystem.demo.dao.UserDao;
import org.usersystem.demo.opt.*;
import org.usersystem.demo.pojo.UserInfo;

import javax.annotation.Resource;
import java.util.*;

@Controller
@CrossOrigin
public class ClientControl {

    @Resource
    UserDao userDao;


    //注册接口
    @RequestMapping(value = "/register/user",method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 registerUser(@RequestBody UserInfo userInfo){
        userInfo.setCreateTime(TimeOpt.getCurrentTime());
        userInfo.setUserId(GetUUID.getUUID());
        System.out.println(userInfo);
        try{
            userDao.insertUser(userInfo);
            return ResponseHelper.create(userInfo, 200, "注册成功！请登录");
        } catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseHelper.create( 500, "注册失败");
        }
    }


    //登陆接口
    @PostMapping(value = "/login/identify")
    @ResponseBody
    public ResponseV2 loginUser(@RequestBody Map paraMap){
        System.out.println(paraMap);
        String userAccount = (String) paraMap.get("userAccount");
        String userPwd = (String) paraMap.get("userPwd");
        UserInfo userInfo = userDao.userExists(userAccount);
        if(userInfo != null){
            if(userPwd.equals(userInfo.getUserPwd())){
                return ResponseHelper.create(userInfo.getUserId(), 200, "验证成功");
            }
            return ResponseHelper.create(500, "密码错误!");
        }
        return ResponseHelper.create(500, "用户不存在!请注册!");
    }
}
