package org.usersystem.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//预约信息
public class AppointmentInfo {
    private String reserve_id;//预约id
    private String user_id;//用户id
    private String illness_content;//病情
    private String dep_id;//科室
    private String staff_id;//预约医生id
    private String staff_name;//预约医生姓名
    private String flag;//来判断是上午预约还是下午
    private String reserve_time;//预约时间
    private String reserve_status;//状态 -1未预约成功 0预约成功 1该预约已完成 1001等待预约 1002用户自己取消预约
    private String create_time;//数据写入时间

}
