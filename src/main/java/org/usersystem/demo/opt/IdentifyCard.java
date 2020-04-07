package org.usersystem.demo.opt;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class IdentifyCard {

    public String identify(String idCard, String name) {
        String host = "https://idcert.market.alicloudapi.com";
        String path = "/idcard";
        String method = "GET";
        String appcode = "8ca45119329f4ac3ab934265727a5fff";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        //前端传入的参数
        querys.put("idCard", idCard);
        querys.put("name", name);
        try {

            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            //获取response的body
            String respBody = EntityUtils.toString(response.getEntity());
            System.out.println("--->" + respBody);
            //响应体
            //{"status":"01","msg":"实名认证通过！","idCard":"150202199803130915","name":"张鹏","sex":"男","area":"内蒙古自治区包头市东河区","province":"内蒙古自治区","city":"包头市","prefecture":"东河区","birthday":"1998-03-13","addrCode":"150202","lastCode":"5"}
            JSONObject jsonObject = JSONObject.parseObject(respBody);
            String status = (String) jsonObject.get("status");
//            String status = "01";
            if (status.equals("01")) {
                return "01";
                //验证通过  处理逻辑
            } else {
                //验证不通过  处理逻辑
                return "-1";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "-2";
        }
    }


    public static void main(String[] args) {

        IdentifyCard test = new IdentifyCard();
        String idCard = "150202199803130915";
        String name = "张鹏";
        test.identify(idCard, name);

    }
}