package org.usersystem.demo.controller;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.usersystem.demo.dao.CaseDao;
import org.usersystem.demo.opt.ResponseHelper;
import org.usersystem.demo.opt.ResponseV2;
import org.usersystem.demo.pojo.CaseInfo;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class CaseControl {

    @Resource
    CaseDao illnessDao;

    @RequestMapping(value = "/case/history" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 getIllnessHistory(@RequestBody JSONObject para){
        String userId = para.getString("userId");
        List<CaseInfo> caseInfoList = illnessDao.getIllnessHistoryById(userId);
        for (CaseInfo caseInfo: caseInfoList) {
            caseInfo.setMedicListJson(JSONArray.parseArray(caseInfo.getMedicList()));
            caseInfo.setMedicList("");
        }
        return ResponseHelper.create(caseInfoList,200,"历史病例查询成功!");
    }



    @RequestMapping(value = "/case/pay" ,method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 paymoney(@RequestBody JSONObject jsonObject){
        String caseId = jsonObject.getString("caseId");
        System.out.println(caseId);
        try{
            illnessDao.updatePayStatus(caseId);
            return ResponseHelper.create(200,"支付成功");
        }catch (Exception e){
            return ResponseHelper.create(500, "支付失败");
        }
    }
}
