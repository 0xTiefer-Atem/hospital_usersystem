package org.usersystem.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//用户注册信息
public class UserInfo {
    private String user_id;//唯一ID
    private String user_name;//姓名
    private String user_sex;//性别
    private String user_tel;//电话
    private String card_id;//用户身份证
    private String user_email;//邮件
    private String user_pwd;//密码
    private String user_birth;//出生日期
    private String user_create;//创建时间
    private String create_time;//数据写入时间
}
