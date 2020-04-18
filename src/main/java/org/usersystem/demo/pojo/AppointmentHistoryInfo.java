package org.usersystem.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppointmentHistoryInfo {
    private String appointmentId;//预约id
    private String appointmentTime;//预约时间
    private String status;//预约状态
    private String staffId;//职员Id
    private String staffName;//职工姓名
    private String cliId;//科室id
    private String cliName;//科室名称
}
