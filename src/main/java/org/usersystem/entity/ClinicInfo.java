package org.usersystem.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 科室信息表
 * @TableName clinicInfo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClinicInfo implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 科室id
     */
    private String cliId;

    /**
     * 科室名称
     */
    private String cliName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 药品列表
     */
    private String medicMenusList;
}