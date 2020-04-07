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
    private String reserve_id;//预约id
    private String user_id;//用户id
    private String user_name;//用户名
    private String user_tel;//用户电话
    private String illness_content;//病情描述
    private String reserve_time;//预约时间
    private String reserve_status;//预约状态
    private String staff_name;//职工姓名
    private String dep_name;//科室名称
}
