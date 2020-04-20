package org.usersystem.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.usersystem.demo.dao.UserDao;
import org.usersystem.demo.opt.GetUUID;
import org.usersystem.demo.opt.TimeOpt;
//import org.usersystem.demo.pojo.MedicalInfo;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Resource
    UserDao userDao;

    @Test
    public void contextLoads() {
        String reg = "[A-z]+[A-z0-9_-]*\\@[A-z0-9]+\\.[A-z]+";
        System.out.println("wyp55023@163.com".matches(reg) + "=============");

        String phone = "13123456789";
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if (phone.length() != 11) {
            System.out.println("手机号应为11位数");
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (isMatch) {
                System.out.println("您的手机号" + phone + "是正确格式@——@");
            } else {
                System.out.println("您的手机号" + phone + "是错误格式！！！");
            }
        }
    }

//    @Test
//    public void my() {
//        String[] types = {"1", "2"};
//        String[] medical_names = {"阿司匹林(001)", "感冒颗粒(002)", "感冒冲剂(003)", "点滴(004)"};
//        String[] medical_nums = {"1", "2", "3"};
//        List<MedicalInfo> medicalInfoList = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            int random = (int) (Math.random() * 10);
//            String type = types[random % 2];
//            String medical_name = medical_names[random % 4];
//            String medical_num = medical_nums[random % 3];
//            MedicalInfo medicalInfo = new MedicalInfo();
//            medicalInfo.setMedical_name(medical_name);
//            medicalInfoList.add(medicalInfo);
//        }
//        System.out.println(JSON.toJSONString(medicalInfoList));
//
//    }

    @Test
    public void test111() {
        String start_time = TimeOpt.getCurrentTime();
        String end_time = TimeOpt.getFetureDate(7);
        System.out.println(start_time + "\t" + end_time);
    }


    @Test
    public void test222() {

        System.out.println(GetUUID.getUUID());
        String date = "2019-06-10 10:05:00";
//        int flag = TimeOpt.moringOrAfterNoon(date);
//        System.out.println(flag);
        String date1 = date.split(" ")[0]+" 08:00:00";
        System.out.println(date1);

    }

    @Test
    public void test(){

        String date1 = "2019-07-03 23:59:59";
        String date2 = "2019-07-03 08:00:00";
        boolean flag = TimeOpt.compareDate(date1,date2);
        System.out.println(flag);


    }

    @Test
    public void test123(){
        TreeMap t = new TreeMap();
        t.put("101","Joe");
        t.put("105","Andy");
        t.put("103","Kelly");
        t.put("105","Sam");
        System.out.println(t);

    }
}
