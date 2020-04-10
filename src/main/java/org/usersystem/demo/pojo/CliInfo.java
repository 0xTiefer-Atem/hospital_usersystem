package org.usersystem.demo.pojo;

//科室信息

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CliInfo {
    private String cliId;
    private String cilName;
}
