package org.usersystem.demo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlackListInfo {
    private String user_id;//用户id
    private String start_time;//入黑名单开始时间
    private String end_time;//入黑名单开始时间
    private String create_time;//数据写入时间
}
