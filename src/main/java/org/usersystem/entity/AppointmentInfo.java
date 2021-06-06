package org.usersystem.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户预约表
 *
 * @TableName appointmentInfo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentInfo implements Serializable {
    /**
     * 自增主键
     */
    private Integer id;

    /**
     * 预约id
     */
    private String appointmentId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 医生id
     */
    private String staffId;

    /**
     * 预约时间
     */
    private Date appointmentTime;

    /**
     * 申请时间
     */
    private Date createTime;

    /**
     * WAIT是在等待挂号,SUCCESS是挂号成功
     */
    private String status;

}