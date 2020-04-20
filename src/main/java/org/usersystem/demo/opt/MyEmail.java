//package org.usersystem.demo.opt;
//
//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//import java.util.Random;
//
//public class MyEmail{
//
//    private String randomCode;
//    private static String EMAIL_FROM = "1144502582@qq.com";
//    private static String PASSWD = "rmhqbthluatyfebd";
//    private static String HOST = "smtp.qq.com";
//    private static String EMAIL_TO;
//    private static int SIGN = -1;//状态0为注册,1为更改密码
//
//    public MyEmail(String email_to) {
//        this.EMAIL_TO = email_to;
//    }
//
//    public String sendMsg(int sign) {
//        //创建邮件配置文件
//        Properties properties = new Properties();
//        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("mail.transport.protocol", "smtp");
//        // 设置邮件服务器
//        properties.setProperty("mail.smtp.host", HOST);
//
//        properties.put("mail.smtp.ssl.enable", "true");//开启ssl
//        // 获取默认session对象
//        Session session = Session.getInstance(properties);
//        session.setDebug(false);
//        Transport ts = null;
//        try {
//            ts = session.getTransport();
//        } catch (NoSuchProviderException e) {
//            return "1006";
//        }
//        try {
//            ts.connect(EMAIL_FROM, PASSWD);
//        } catch (MessagingException e) {
//            return "1006";
//        }
//        Message message = null;
//        try {
//            if(sign==0) {
//                message = createSimpleMail(session);//用户注册
//            }else {
//                message = createUpDateMsg(session);//更改密码
//            }
//            ts.sendMessage(message, message.getAllRecipients());
//        } catch (MessagingException e) {
//            return "1006";
//        }
//        return "200";
//    }
//
//    private MimeMessage createSimpleMail(Session session) throws MessagingException {
//        randomCode = getMailCode();
//
//        //创建邮件对象
//        MimeMessage mm = new MimeMessage(session);
//        //设置发件人
//        mm.setFrom(new InternetAddress(EMAIL_FROM));
//        //设置收件人
//        //mm.addRecipients(Message.RecipientType.CC, InternetAddress.parse(EMAIL_FROM));
//        mm.setRecipient(Message.RecipientType.TO, new InternetAddress(EMAIL_TO));
//        mm.setSubject("账户注册验证");
//        mm.setContent("您的验证码为: <span style='color: blue'>"+randomCode+"</span> 请不要轻易告诉他人", "text/html;charset=utf-8");
//        return mm;
//    }
//
//
//    private MimeMessage createUpDateMsg(Session session) throws MessagingException {
//        randomCode = getMailCode();
//        //创建邮件对象
//        MimeMessage mm = new MimeMessage(session);
//        //设置发件人
//        mm.setFrom(new InternetAddress(EMAIL_FROM));
//        //设置收件人
//        mm.addRecipients(Message.RecipientType.CC, InternetAddress.parse(EMAIL_FROM));
//        mm.setRecipient(Message.RecipientType.TO, new InternetAddress(EMAIL_TO));
//        mm.setSubject("账户更改密码验证");
//        mm.setContent("您的账户修改验证码为: <span style='color: blue'>"+randomCode+"</span> 请不要轻易告诉他人", "text/html;charset=utf-8");
//
//        return mm;
//    }
//
//    public String getRandomCode() {
//        return randomCode;
//    }
//
//    public static String getMailCode() {
//        /*
//         * 返回长度为【strLength】的随机数，在前面补0
//         */
//        int strLength = 4;
//        Random rm = new Random();
//        // 获得随机数
//        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);
//        // 将获得的获得随机数转化为字符串
//        String fixLenthString = String.valueOf(pross);
//        String mailCode = fixLenthString.substring(1, strLength + 1);
//        // 返回固定的长度的随机数
//        return mailCode;
//    }
//}
