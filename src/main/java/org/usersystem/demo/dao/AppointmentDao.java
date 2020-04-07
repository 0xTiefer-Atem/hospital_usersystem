package org.usersystem.demo.dao;

import org.apache.ibatis.annotations.*;
import org.usersystem.demo.pojo.DoctorInfo;
import org.usersystem.demo.pojo.AppointmentHistoryInfo;
import org.usersystem.demo.pojo.AppointmentInfo;
import java.util.List;
import java.util.Map;

@Mapper
public interface AppointmentDao {
    @Insert("insert into reserve_info " +
            "(reserve_id, user_id, illness_content,dep_id," +
            "staff_id, reserve_time, reserve_status,create_time) " +
            "values(#{reserve_id},#{user_id},#{illness_content},#{dep_id}," +
            "#{staff_id},#{reserve_time},#{reserve_status},#{create_time}) ")
    void addReserve(AppointmentInfo reserveInfo);

    @Select("select distinct distinct reserve_id,r.user_id,user_name,user_tel,illness_content," +
            "reserve_time,reserve_status,staff_name,dep_name" +
            " from reserve_info r left join user_info on r.user_id = user_info.user_id" +
            " join dep_info d on r.dep_id = d.dep_id left join staff_info s" +
            " on r.staff_id = s.staff_id where r.user_id=#{user_id} " +
            " order by reserve_time ")
    List<AppointmentHistoryInfo> searchReserveHistory(Map<String,String> paraMap);

    @Select("select * from reserve_info where reserve_id=#{reserve_id}")
    AppointmentInfo reserveInfoDetail(String reserve_id);

    @Delete("delete from reserve_info where reserve_id = #{reserve_id} and user_id = #{user_id} ")
    int deleteReserve(Map<String,String> para);


    @Select("select doctor_id, staff_name,staff_sex,staff_pos,work_time,work_status " +
            "from doctor_info d left join staff_info s on d.doctor_id = s.staff_id " +
            "where  to_days(work_time) >= to_days(now()) " +
            " and to_days(work_time) < to_days(#{future_time}) and d.doctor_id like " +
            "CONCAT(#{dep_id},'%') ")
    List<DoctorInfo> getWorkTimeToday(Map<String,String> para);


    @Select("select doctor_id, staff_name,staff_sex,staff_pos,work_time,work_status" +
            "            from doctor_info d left join staff_info s on d.doctor_id = s.staff_id" +
            "            where  to_days(work_time) >= to_days(now())" +
            "             and to_days(work_time) <= to_days(#{future_time})" +
            "            and d.doctor_id like CONCAT(#{dep_id},'%') order by work_time asc")
    List<DoctorInfo> getWorkTimeSevenDays(Map<String,String> para);


    @Select("select count(reserve_id) from reserve_info where staff_id = #{staff_id} " +
            "and reserve_time between #{work_time1} and #{work_time2}")
    int getReserveNum(Map<String,String> para);
}