package org.usersystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DoctorWorkInfo {
    private String appointmentTime;//已预约的时间
    private int morningNum; //上午预约几个
    private int afternoonNum; //下午预约几个
}
