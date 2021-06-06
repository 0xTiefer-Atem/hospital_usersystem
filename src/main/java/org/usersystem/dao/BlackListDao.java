//package org.usersystem.demo.dao;
//
//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//import org.usersystem.demo.pojo.BlackListInfo;
//
//@Mapper
//public interface BlackListDao {
//    @Insert("insert into blacklist_info(user_id,start_time,end_time)" +
//            " values(#{user_id},#{start_time},#{end_time})")
//    void insterBlackUser(BlackListInfo blackListInfo);
//
//    @Delete("delete from blacklist_info where user_id = #{user_id}")
//    void deleteBlackUser(String user_id);
//
//    @Select("select * from blacklist_info where user_id = #{user_id}")
//    BlackListInfo searchBalckUserById(String user_id);
//}
