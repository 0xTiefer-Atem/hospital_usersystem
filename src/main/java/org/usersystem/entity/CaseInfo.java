package org.usersystem.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 病例详情表
 *
 * @TableName caseInfo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CaseInfo {
    /**
     * 病历录入表
     */
    private Integer id;

    /**
     * 病历单id
     */
    private String caseId;

    /**
     * 预约id
     */
    private String registerId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 医生id
     */
    private String staffId;

    /**
     * 病例描述
     */
    private String userIllness;

    /**
     * 开的药的列表
     */
    private String medicList;

    /**
     * 总的价钱
     */
    private Integer totalPrice;

    /**
     * UNPAY是未缴费，PAY是已缴费
     */
    private String payStatus;

    /**
     * 用户反馈
     */
    private String feedBack;

    /**
     * 创建时间
     */
    private Date createTime;
}