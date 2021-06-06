package org.usersystem.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.usersystem.pojo.CliInfo;
import org.usersystem.pojo.StaffInfo;

import java.util.List;

@Mapper
public interface OtherDao {
    @Select("select cliId,cliName from clinicInfo")
    List<CliInfo> getCliInfo();

    @Select("select staffId,staffName from staffInfo where staffId like CONCAT(#{id},'%') ")
    List<StaffInfo> getStaffInfo(String id);

    @Select("select * from staffInfo")
    List<StaffInfo> getStaffInfoList();

}
