package org.usersystem.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.usersystem.entity.AppointmentInfo;

@Mapper
public interface AppointmentInfoMapper {

    /**
     * 根据预约Id查询预约信息
     * @param appointmentId
     * @return
     */
    AppointmentInfo getAppointmentInfoById(@Param("appointmentId") String appointmentId);





}
