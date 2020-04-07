package org.usersystem.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.usersystem.demo.dao.UserDao;
import org.usersystem.demo.opt.*;
import org.usersystem.demo.pojo.UserInfo;

import java.util.*;

@Controller
@CrossOrigin
public class ClientControl {

    @Autowired
    UserDao userDao;

    private OptUserJedis optUserJedis = new OptUserJedis();
    @RequestMapping(value = "/register/sendCode",method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 registerUser(@RequestBody UserInfo userInfo){
        Map<String,String> resultMap = new HashMap<>();
//        IdentifyCard identifyCard = new IdentifyCard();
        String user_id = GetUUID.getUUID();
        userInfo.setUser_id(user_id);
        userInfo.setUser_create("");
        System.out.println(userInfo);
        optUserJedis.setUserDao(userInfo);
        String randomCode = optUserJedis.sendCode(userInfo,userDao);
        switch (randomCode){
            case "1006":
                return ResponseHelper.create(null,1006,"验证码发送失败!请稍候再试");
            case "1007":
                return ResponseHelper.create(null,1007,"该用户已存在,请去登录");
        }
        resultMap.put("user_id",user_id);
//        resultMap.put("randomCode",randomCode);
        return ResponseHelper.create(resultMap,200,"注册成功!请注意查收验证码(有效5分钟)");
    }

    @RequestMapping(value = "/register/identify",method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 identifyUser(@RequestBody JSONObject paraJson){
        String user_id = paraJson.getString("user_id");
        String user_code = paraJson.getString("user_code");
        ResponseV2 responseV2 = optUserJedis.identifyMsg(user_id,user_code,userDao);
        return responseV2;
    }

    @RequestMapping(value = "/login/identify",method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 loginIdentiyf(@RequestBody JSONObject userLoginJsonObject){
        String user_type = userLoginJsonObject.getString("user_type");
        String user_account = userLoginJsonObject.getString("user_account");
        String user_pwd = userLoginJsonObject.getString("user_pwd");
        ResponseV2 responseV2 = optUserJedis.loginIdentify(user_type,user_account,user_pwd,userDao);
        return responseV2;
    }
}
