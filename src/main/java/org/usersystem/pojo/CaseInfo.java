package org.usersystem.pojo;

import com.alibaba.fastjson.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//病例信息
//多条为看病历史
@ToString
public class CaseInfo {
    private String caseId;//病例id
    private String cliName;//科室名称
    private String userName;//用户姓名
    private String userIllness;//病情描述
    private String staffName;//治疗医生姓名
    private transient String medicList;//药物清单
    private float totalPrice;//总缴费金额
    private String payStatus;//缴费状态 UNPAY是未缴费，PAY是已缴费
    private String createTime;//病例生成日期
    private JSONArray medicListJson;//药物清单json格式
}
