package org.usersystem.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//用户注册信息
public class UserInfo {
    private String userId;//唯一ID
    private String userName;//姓名
    private String userSex;//性别
    private String userTel;//电话
    private String userEmail;//邮件
    private String userPwd;//密码
    private String createTime;//数据写入时间
}
