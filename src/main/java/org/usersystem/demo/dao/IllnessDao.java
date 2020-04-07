package org.usersystem.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.usersystem.demo.pojo.IllnessInfo;
import org.usersystem.demo.pojo.MedicalInfo;

import java.util.List;
import java.util.Map;

@Mapper
public interface IllnessDao {
    @Select("select illness_id,user_name,illness_desc," +
            " medical_id_num,total_money,pay_status,illness_time" +
            " from illness_info i left join user_info u on" +
            " i.user_id = u.user_id" +
            " where i.user_id = #{user_id} order by illness_time desc")
    List<IllnessInfo> getIllnessHistoryById(Map<String,String> para);


    @Update("update illness_info set pay_status = 'PAY' where illness_id = #{illness_id}")
    void updatePayStatus(Map<String,String> para);


    @Select("select medical_id,medical_name,medical_price from medical_info where medical_id = #{medical_id}  ")
    MedicalInfo getMedicalById(String medical_id);

}
