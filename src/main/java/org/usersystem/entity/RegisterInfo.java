package org.usersystem.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 挂号信息表
 * @TableName registerInfo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterInfo {
    /**
     * 
     */
    private Integer id;

    /**
     * 挂号id
     */
    private String registerId;

    /**
     * 预约id
     */
    private String appointmentId;

    /**
     * 医生id
     */
    private String staffId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * WAIT是等待就诊,SUCCESS是等待开病例,FINISH是开病例完成
     */
    private String status;
}