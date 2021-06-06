package org.usersystem.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.usersystem.dao.CaseDao;
import org.usersystem.opt.ResponseHelper;
import org.usersystem.opt.ResponseV2;
import org.usersystem.pojo.CaseInfo;

import javax.annotation.Resource;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "/api")
public class CaseControl {

    @Resource
    CaseDao illnessDao;

    @RequestMapping(value = "/case/history", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 getIllnessHistory(@RequestBody JSONObject para) {
        String userId = para.getString("userId");
        List<CaseInfo> caseInfoList = illnessDao.getIllnessHistoryById(userId);
        for (CaseInfo caseInfo : caseInfoList) {
            caseInfo.setMedicListJson(JSONArray.parseArray(caseInfo.getMedicList()));
            caseInfo.setMedicList("");
        }
        return ResponseHelper.create(caseInfoList, 200, "历史病例查询成功!");
    }


    @RequestMapping(value = "/case/pay", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 payMoney(@RequestBody JSONObject jsonObject) {
        String caseId = jsonObject.getString("caseId");
        System.out.println(caseId);
        try {
            illnessDao.updatePayStatus(caseId);
            return ResponseHelper.create(200, "支付成功");
        } catch (Exception e) {
            return ResponseHelper.create(500, "支付失败");
        }
    }
}
