package org.usersystem.demo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//挂号表
public class RegisterInfo {
    private String register_id;//挂号id
    private String reserve_id;//预约id
    private String staff_id;//医生id
    private String wait_status;//排队状态 -1 未排队 0已排上 1已完成看病

    /*登记状态
      1010已预约且在预约时间之前登记
    */
    private String register_status;

    private String register_time;//挂号时间
    private String create_time;//数据写入时间

}
