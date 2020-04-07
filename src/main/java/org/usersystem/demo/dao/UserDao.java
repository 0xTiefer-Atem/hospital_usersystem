package org.usersystem.demo.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.usersystem.demo.pojo.UserInfo;

@Mapper
public interface UserDao {
    @Insert("insert into user_info " +
            "(user_id, user_name, user_sex, " +
            "user_tel,user_email, user_pwd, " +
            "user_birth, user_create,create_time,card_id)" +
            "values(#{user_id},#{user_name},#{user_sex}," +
            "#{user_tel},#{user_email},#{user_pwd}," +
            "#{user_birth},#{user_create},#{create_time},#{card_id}) ")
    void insertUser(UserInfo userInfo);

    @Select("select user_pwd from user_info where user_email=#{user_email}")
    String searchPwdByEmail(String user_email);

    @Select("select user_pwd from user_info where user_tel=#{user_tel}")
    String searchPwdByTel(String user_tel);

    @Select("select count(*) from user_info where user_email = #{user_email}")
    int userExists(String user_email);

    @Select("select user_id from user_info where user_email = #{user_email}")
    String searchIdByEmail(String user_eamil);

    @Select("select user_id from user_info where user_tel = #{user_tel}")
    String searchIdByTel(String user_tel);
}
