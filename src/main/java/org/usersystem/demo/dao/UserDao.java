package org.usersystem.demo.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.usersystem.demo.pojo.UserInfo;

@Mapper
public interface UserDao {
    @Insert("insert into userInfo " +
            "(userId, userName, userSex, " +
            "userTel,userEmail, userPwd, " +
            "createTime)" +
            "values(#{userId},#{userName},#{userSex}," +
            "#{userTel},#{userEmail},#{userPwd}," +
            "#{createTime}) ")
    void insertUser(UserInfo userInfo);

    @Select("select user_pwd from user_info where user_email=#{user_email}")
    String searchPwdByEmail(String user_email);

    @Select("select user_pwd from user_info where user_tel=#{user_tel}")
    String searchPwdByTel(String user_tel);

    @Select("select * from userInfo where userTel = #{userTel} ")
    UserInfo userExists(String userTel);
}
