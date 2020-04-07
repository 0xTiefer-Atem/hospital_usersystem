package org.usersystem.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.usersystem.demo.pojo.CaseInfo;
import org.usersystem.demo.pojo.MedicalInfo;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaseDao {
    @Select("select distinct cI.caseId, cI.userIllness, uI.userName,clI.cliName, sI.staffName, " +
            "cI.medicList, cI.totalPrice, cI.payStatus, cI.createTime " +
            "from caseInfo cI, staffInfo sI, " +
            "userInfo uI , clinicInfo clI" +
            " where cI.userId = uI.userId" +
            " and cI.staffId = sI.staffId" +
            " and sI.staffId like concat(clI.cliId,'%')" +
            " and cI.userId = #{id}")
    List<CaseInfo> getIllnessHistoryById(String id);


    @Update("update illness_info set pay_status = 'PAY' where illness_id = #{illness_id}")
    void updatePayStatus(Map<String,String> para);


    @Select("select medical_id,medical_name,medical_price from medical_info where medical_id = #{medical_id}  ")
    MedicalInfo getMedicalById(String medical_id);

}
