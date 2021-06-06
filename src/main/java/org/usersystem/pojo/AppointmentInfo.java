package org.usersystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//预约信息
public class AppointmentInfo {
    private String appointmentId;//预约id
    private String userId;//用户id
    private String staffId;//预约医生id
    private String appointmentTime;//预约时间
    private String status;//状态
    private String createTime;//数据写入时间

}
