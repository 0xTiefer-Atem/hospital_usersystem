/*
 * @(#) SmsSendDemo.java 2018-11-29
 *
 * Copyright 2018 NetEase.com, Inc. All rights reserved.
 */

package org.usersystem.demo.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.Consts;
import org.apache.http.client.HttpClient;

import com.alibaba.fastjson.JSONObject;

/**
 * @author haoshijing
 * @version 2018-11-29
 */
public class SmsSendDemo {
    /**
     * 业务ID，易盾根据产品业务特点分配
     */
    private final static String BUSINESSID = "c89d931d9a6f418ba7f9b8c10fab73ed";
    /**
     * 产品密钥ID，产品标识
     */
    private final static String SECRETID = "95ff220f155e05f21aa9e34b18339055";
    /**
     * 产品私有密钥，服务端生成签名信息使用，请严格保管，避免泄露
     */
    private final static String SECRETKEY = "990c11a920d6ab421f671b7681766852";

    /**
     * 接口地址
     */
    private final static String API_URL = "https://sms.dun.163yun.com/v2/sendsms";
    /**
     * 实例化HttpClient，发送http请求使用，可根据需要自行调参
     */
    private static HttpClient httpClient = HttpClient4Utils.createHttpClient(100, 20, 10000, 2000, 2000);



     public static void TextMessage(String code,String mobile) throws IOException {
         String templateId="10699";
         // 模板参数对应的json格式数据,例如模板为您的验证码为${p1},请于${p2}时间登陆到我们的服务器
         JSONObject jsonObject = new JSONObject();
         jsonObject.put("code",code);
         jsonObject.put("p2", "20180816");
         String params = jsonObject.toJSONString();
         Map<String, String> datas = buildParam(mobile, templateId, params);
         String result = HttpClient4Utils.sendPost(httpClient, API_URL, datas, Consts.UTF_8);
         System.out.println("result = [" + result + "]");
     }
      private static Map<String, String> buildParam(String mobile, String templateId, String params) throws IOException {
        Map map = new HashMap<String, String>();
        map.put("businessId", BUSINESSID);
        map.put("timestamp", String.valueOf(System.currentTimeMillis()));
        map.put("version", "v2");
        map.put("needUp", "false");
        map.put("templateId", templateId);
        map.put("mobile", mobile);
        map.put("paramType", "json");
        map.put("params", params);
        map.put("nonce", UUID.randomUUID().toString().replace("-", ""));
        map.put("secretId", SECRETID);
        String sign = SignatureUtils.genSignature(SECRETKEY, map);
        map.put("signature", sign);
        return map;
      }
    /**
     * @param args
     * @throws Exception
     */




    //测试方法
    public static void main(String[] args) throws Exception {
        //两个参数 code 4位随机数  monile： 手机号
        SmsSendDemo.TextMessage("8888","17602617335");
        // 此处用申请通过的模板id

    }
}
