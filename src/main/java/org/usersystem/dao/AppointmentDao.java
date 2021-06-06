package org.usersystem.dao;

import org.apache.ibatis.annotations.*;
import org.usersystem.pojo.AppointmentHistoryInfo;
import org.usersystem.pojo.AppointmentInfo;
import org.usersystem.pojo.DoctorWorkInfo;

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

    @Select("select distinct ap.appointmentId," +
            "       ap.appointmentTime," +
            "       ap.staffId," +
            "       sI.staffName," +
            "       ap.status," +
            "       cliId," +
            "       cliName" +
            " from appointmentInfo ap, staffInfo sI ,clinicInfo cI , userInfo uI" +
            " where ap.staffId = sI.staffId" +
            " and ap.userId = uI.userId" +
            " and ap.staffId like CONCAT(cliId,'%')" +
            " and ap.userId = #{userId} order by ap.appointmentTime desc")
    List<AppointmentHistoryInfo> searchReserveHistory(String userId);

    // where r.user_id=#{user_id} and reserve_time >= #{start_time} and reserve_time <= #{end_time}
    //desc limit #{start},#{size}

    @Select("select * from reserve_info where reserve_id=#{reserve_id}")
    AppointmentInfo reserveInfoDetail(String reserve_id);

    @Delete("delete from appointmentInfo where appointmentId = #{appointmentId}")
    int deleteAppointment(String appointmentId);


    @Select("select doctor_id, staff_name,staff_sex,staff_pos,work_time,work_status " +
            "from doctor_info d left join staff_info s on d.doctor_id = s.staff_id " +
            "where  to_days(work_time) >= to_days(now()) " +
            " and to_days(work_time) < to_days(#{future_time}) and d.doctor_id like " +
            "CONCAT(#{dep_id},'%') ")
    List<DoctorWorkInfo> getWorkTimeToday(Map<String, String> para);


    @Select("select distinct" +
            " ap.appointmentTime" +
            " from appointmentInfo ap" +
            " where ap.staffId = #{staffId}" +
            "  and ap.appointmentTime between #{startTime} and #{endTimeTime}" +
            "  and ap.status = 'WAIT'")
    List<String> getWorkTimeSevenDays(Map<String, String> para);


    @Select("select appointmentId from appointmentInfo" +
            " where userId = #{id} and" +
            " DATE_FORMAT(appointmentTime,'%Y-%m-%d') = #{date} and" +
            " status = 'WAIT'")
    List<String> getAppointmentNum(String id, String date);
}
