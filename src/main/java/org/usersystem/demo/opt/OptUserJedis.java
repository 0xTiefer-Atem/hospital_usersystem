//package org.usersystem.demo.opt;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.usersystem.demo.dao.UserDao;
//import org.usersystem.demo.pojo.UserInfo;
//import org.usersystem.demo.utils.SmsSendDemo;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import javax.annotation.Resource;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///*
//* 用于处理注册,使用redis的第1个数据库存用户信息
//* 第0个存id:randomCode
//* */
//
//
//
//public class OptUserJedis {
//
//    @Resource
//    UserDao userDao;
//
//
//    private static final Logger logger = Logger.getLogger(OptUserJedis.class);
//    private MyEmail myEmail;
//    private String response_json;
//    private static String USER_EMAIL = "email";
//    public static JedisPool jedisPool;
//    private Date date = new Date();
//    private static int REGISTE = 0;
//    private static int CHANGE = 1;
//    public String status = "";
//
//    public OptUserJedis() {
//        logger.info("开始初始化jedispool");
//        if (jedisPool == null) {
//            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxTotal(50);
//            //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
//            config.setMaxIdle(5);
//            config.setMinIdle(2);
//            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
//            // 单位毫秒 //小于零:阻塞不确定的时间, 默认-1
//            config.setMaxWaitMillis(1000 * 100);
//            //在borrow(引入)一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
//            config.setTestOnBorrow(true);
//            //return 一个jedis实例给pool时，是否检查连接可用性（ping()）
//            config.setTestOnReturn(true);
//            //connectionTimeout 连接超时（默认2000ms）
//            // soTimeout 响应超时（默认2000ms）
//            jedisPool = new JedisPool(config, "127.0.0.1", 6379);
//        }
//    }
//
//    public void setUserDao(UserInfo user) {
//        Jedis jedis = jedisPool.getResource();
//        jedis.select(1);
//        String user_msg = JSON.toJSONString(user);
//        logger.info("正在将用户: " + user_msg + " 写入redis...");
//        jedis.set(user.getUserId(), user_msg);
//        logger.info("写入成功");
//        jedis.close();
//    }
//
//    //用户注册
//    public String sendCode(UserInfo userInfo,UserDao userDao){
//        Jedis jedis = jedisPool.getResource();
//        jedis.select(0);
//        String user_id = userInfo.getUserId();
//        String tel = userInfo.getUserTel();
//        String user_eamil = userInfo.getUserEmail();
//        String result = "1006";
//        String randomCode = "";
//        if( tel!=null&&!tel.equals("")){
//            status = "短信";
//            try {
//                randomCode = MyEmail.getMailCode();
//                System.out.println(randomCode+"   "+tel);
//                SmsSendDemo.TextMessage(randomCode,tel);
//                jedis.set(user_id + ":" + randomCode, "off");
//                result ="200";
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }else {
//            UserInfo flag = userDao.userExists(user_eamil);
//            if (flag != null) {
//                return "1007";
//            }
//            myEmail = new MyEmail(user_eamil);
//            result = myEmail.sendMsg(REGISTE);
//            randomCode = myEmail.getRandomCode();
//            jedis.set(user_id + ":" + randomCode, "off");
////            jedis.expire(user_id+":"+randomCode,300);
//            status = "邮箱";
//        }
//
//        if(result.equals("200")){
//            return randomCode;
//        }
//        return result;
//    }
//
//
//    public ResponseV2 identifyMsg(String user_id,String user_code,UserDao userDao){
//        Jedis jedis = jedisPool.getResource();
//        jedis.select(0);
//        String status = jedis.get(user_id+":"+user_code);
//        if(status==null){
//            return ResponseHelper.create(null,1001,"未匹配到用户");
//        }
//        jedis.del(user_id+":"+user_code);
//        jedis.set(user_id,"on");
//        jedis.select(1);
//        String user_msg = jedis.get(user_id);
//        if(user_msg == null){
//            return ResponseHelper.create(null,1002,"用户不存在,请重新注册");
//        }
//
//        UserInfo userInfo = JSON.parseObject(user_msg,UserInfo.class);
//        logger.info("查出用户信息： "+user_msg);
//        String create_time = TimeOpt.getCurrentTime();
//        userInfo.setCreateTime(create_time);
//        userDao.insertUser(userInfo);
//        Map<String,String> resultMap = new HashMap<>();
//        resultMap.put("user_id",userInfo.getUserId());
//        return ResponseHelper.create(resultMap,200,"用户创建成功!请重新登录！");
//    }
//
//
//    public ResponseV2 loginIdentify(String user_type,String user_account,String user_pwd,UserDao userDao){
//        String user_pwd1 = null;
//        switch (user_type){
//            case "tel":
//                user_pwd1 = userDao.searchPwdByTel(user_account);
//                break;
//            case "email":
//                user_pwd1 = userDao.searchPwdByEmail(user_account);
//                break;
//        }
//        if(user_pwd1 == null){
//            return ResponseHelper.create(null,1004,"用户不存在");
//        }
//        if(!user_pwd1.equals(user_pwd)){
//            return ResponseHelper.create(null,1005,"密码不匹配");
//        }
//        String user_id = "";
//        switch (user_type){
//            case "tel":
//                user_id = userDao.searchPwdByTel(user_account);
//                break;
//            case "email":
//                user_id  = userDao.searchPwdByEmail(user_account);
//                break;
//        }
//        return ResponseHelper.create(user_id,200,"密码正确");
//    }
//}
