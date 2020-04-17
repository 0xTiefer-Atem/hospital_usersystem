package org.usersystem.demo.dao;

import org.apache.ibatis.annotations.*;
import org.usersystem.demo.pojo.AppointmentHistoryInfo;
import org.usersystem.demo.pojo.AppointmentInfo;
import org.usersystem.demo.pojo.DoctorWorkInfo;

import java.util.List;
import java.util.Map;

@Mapper
public interface AppointmentDao {
    @Insert("insert into appointmentInfo " +
            "(appointmentId, userId, " +
            "staffId, appointmentTime, status,createTime) " +
            "values(#{appointmentId},#{userId}," +
            "#{staffId},#{appointmentTime},#{status},#{createTime}) ")
    void addAppointmentInfo(AppointmentInfo appointmentInfo);

    @Select("select reserve_id,r.user_id,user_name,user_tel,illness_content," +
            "reserve_time,reserve_status,staff_name,dep_name" +
            " from reserve_info r left join user_info on r.user_id = user_info.user_id" +
            " join dep_info d on r.dep_id = d.dep_id left join staff_info s" +
            " on r.staff_id = s.staff_id where r.user_id=#{user_id} " +
            " order by reserve_time ")
    List<AppointmentHistoryInfo> searchReserveHistory(Map<String,String> paraMap);

    // where r.user_id=#{user_id} and reserve_time >= #{start_time} and reserve_time <= #{end_time}
    //desc limit #{start},#{size}

    @Select("select * from reserve_info where reserve_id=#{reserve_id}")
    AppointmentInfo reserveInfoDetail(String reserve_id);

    @Delete("delete from reserve_info where reserve_id = #{reserve_id} and user_id = #{user_id} ")
    int deleteReserve(Map<String,String> para);


    @Select("select doctor_id, staff_name,staff_sex,staff_pos,work_time,work_status " +
            "from doctor_info d left join staff_info s on d.doctor_id = s.staff_id " +
            "where  to_days(work_time) >= to_days(now()) " +
            " and to_days(work_time) < to_days(#{future_time}) and d.doctor_id like " +
            "CONCAT(#{dep_id},'%') ")
    List<DoctorWorkInfo> getWorkTimeToday(Map<String,String> para);


    @Select("select distinct" +
            " ap.appointmentTime" +
            " from appointmentInfo ap" +
            " where ap.staffId = #{staffId}" +
            "  and ap.appointmentTime between #{startTime} and #{endTimeTime}" +
            "  and ap.status = 'WAIT'")
    List<String> getWorkTimeSevenDays(Map<String,String> para);


    @Select("select count(reserve_id) from reserve_info where staff_id = #{staff_id} " +
            "and reserve_time between #{work_time1} and #{work_time2}")
    int getReserveNum(Map<String,String> para);
}
