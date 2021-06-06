package org.usersystem.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RegisterInfoMapper {

    String getRegisterIdByAppointmentId(@Param("appointmentId") String appointmentId);


}
