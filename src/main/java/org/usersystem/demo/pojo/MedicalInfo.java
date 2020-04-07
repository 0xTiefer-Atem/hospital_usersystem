package org.usersystem.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalInfo {
    private String medical_id;//药物id
    private String medical_name;//药物名称
    private float medical_price;//药物价格
    private String buy_num;//购买数量
}
