package org.usersystem.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 职员信息表
 * @TableName staffInfo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffInfo {
    /**
     * 
     */
    private Integer id;

    /**
     * 医生id
     */
    private String staffId;

    /**
     * 密码
     */
    private String password;

    /**
     * 医生姓名
     */
    private String staffName;

    /**
     * 性别
     */
    private String staffSex;

    /**
     * 电话
     */
    private String staffTel;

    /**
     * 医生职位
     */
    private String staffPos;

    /**
     * 
     */
    private Date staffEntry;

    /**
     * 
     */
    private Date createTime;
}