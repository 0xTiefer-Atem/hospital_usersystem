package org.usersystem.demo.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.usersystem.demo.pojo.CliInfo;
import org.usersystem.demo.pojo.StaffInfo;

import java.util.List;

@Mapper
public interface OtherDao {
    @Select("select cliId,cliName from clinicInfo")
    List<CliInfo> getCliInfo();

    @Select("select staffId,staffName from staffInfo where staffId like CONCAT(#{id},'%') ")
    List<StaffInfo> getStaffInfo(String id);

}
