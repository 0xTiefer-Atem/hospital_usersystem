//package org.usersystem.demo.dao;
//
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//import org.apache.ibatis.annotations.Update;
//import org.usersystem.demo.pojo.RegisterHistoryInfo;
//import org.usersystem.demo.pojo.RegisterInfo;
//
//import java.util.HashMap;
//import java.util.List;
//
//@Mapper
//public interface RegisterDao {
//
//    @Insert("insert into register_info " +
//            "(register_id,reserve_id," +
//            "wait_status,register_status" +
//            ",register_time) values(#{register_id}," +
//            "#{reserve_id},#{wait_status},#{register_status}," +
//            "#{register_time})")
//    void addRegisterInfo(RegisterInfo registerInfo);
//
//
//    @Update("update register_info set wait_status = 1 where register_id = #{register}")
//    void updateRegisterInfo(String register_id);
//
//    @Select("select count(*) from register_info where reserve_id = #{reserve_id}")
//    int searchReserveById(String reserve_id);
//
//    @Select("select register_id,rg.reserve_id,treat_status,rg.create_time,user_tel," +
//            " r.user_id,illness_content,r.dep_id,d.dep_name,r.staff_id,s.staff_name,reserve_time,user_name" +
//            " from register_info rg left join reserve_info r on rg.reserve_id = r.reserve_id" +
//            " left join user_info on r.user_id = user_info.user_id" +
//            " join dep_info d on r.dep_id = d.dep_id left join staff_info s" +
//            " on r.staff_id = s.staff_id " +//where r.user_id=#{user_id}
////            " and " +//register_time between #{start_time}" +
//            //" and #{end_time}
//            " order by rg.create_time desc ")
//    //limit #{start},#{size}
//    List<RegisterHistoryInfo> searchRegisterHistory(HashMap<String,String> paraMap);
//}
