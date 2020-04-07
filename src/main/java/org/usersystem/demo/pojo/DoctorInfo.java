package org.usersystem.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorInfo {
    private String doctor_id;
    private String staff_name;
    private String staff_sex;
    private String staff_pos;
    private String work_time;
    private String work_status;
    private int am;
    private int pm;
}
