package org.usersystem.demo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.usersystem.demo.dao.IllnessDao;
import org.usersystem.demo.opt.ResponseHelper;
import org.usersystem.demo.opt.ResponseV2;
import org.usersystem.demo.pojo.IllnessInfo;
import org.usersystem.demo.pojo.MedicalInfo;

import javax.print.attribute.HashAttributeSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class IllnessControl {
    private static final HashMap<String,String> medical_map = new HashMap<>();
    static {
        medical_map.put("1","阿莫西林");
        medical_map.put("2","六味地黄丸");
        medical_map.put("3","伸腿瞪眼丸");
        medical_map.put("4","包治百病");
        medical_map.put("5","点滴");
    }
    @Autowired
    IllnessDao illnessDao;

    @RequestMapping("/illness/history")
    @ResponseBody
    public ResponseV2 getIllnessHistory(@RequestBody JSONObject para){
        String user_id = para.getString("user_id");
        HashMap hashMap = new HashMap();
        hashMap.put("user_id", user_id);
        List<IllnessInfo> illnessInfoList = illnessDao.getIllnessHistoryById(hashMap);
        String medical_list = "";
        for (IllnessInfo illness: illnessInfoList) {
            medical_list = illness.getMedical_id_num();
            String[] medicalobjs = medical_list.split(",");
            List<MedicalInfo> medicalInfoList = new ArrayList<>();
            for (String medicalobj: medicalobjs) {
                String medical_id = medicalobj.split(":")[0];
                String medical_num = medicalobj.split(":")[1];
                MedicalInfo medicalInfo = new MedicalInfo();
                medicalInfo.setMedical_id(medical_id);
                medicalInfo.setMedical_name(medical_map.get(medical_id));
                medicalInfo.setBuy_num(medical_num);
                medicalInfoList.add(medicalInfo);
            }
            illness.setMedicalInfoList(medicalInfoList);
        }
        return ResponseHelper.create(illnessInfoList,200,"历史病例查询成功!");
    }



    @RequestMapping(value = "/illness/pay" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 paymoney(@RequestBody JSONObject jsonObject){
        String illness_id = jsonObject.getString("illness_id");
        System.out.println(illness_id);
        Map<String,String> para = new HashMap<>();
        para.put("illness_id",illness_id);
        illnessDao.updatePayStatus(para);
        return ResponseHelper.create(null,200,"支付成功");
    }
}
