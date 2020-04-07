package org.usersystem.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.usersystem.demo.dao.BlackListDao;
import org.usersystem.demo.dao.AppointmentDao;
import org.usersystem.demo.opt.*;
import org.usersystem.demo.pojo.BlackListInfo;
import org.usersystem.demo.pojo.DoctorInfo;
import org.usersystem.demo.pojo.AppointmentHistoryInfo;
import org.usersystem.demo.pojo.AppointmentInfo;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@CrossOrigin
public class AppointmentControl {

    @Resource
    AppointmentDao appointmentDao;

    @Resource
    BlackListDao blackListDao;

    private static String[] morningTimes = {" 08:00:00"," 09:00:00"," 10:00:00"};
    private static String[] afternoonTimes = {" 13:00:00"," 14:00:00"," 15:00:00"};

    //录入预约信息
    @RequestMapping(value = "/reserve/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 addReserve(@RequestBody JSONObject jsonObject) {
        String user_id = jsonObject.getString("user_id");
        BlackListInfo blackListInfo = blackListDao.searchBalckUserById(user_id);
        String reserve_date = jsonObject.getString("reserve_time");
        if (blackListInfo != null) {
            String end_time = blackListInfo.getEnd_time();
            boolean is_late = TimeOpt.compareDate(reserve_date + " 01:00:00", end_time);
            if (!is_late) {
                return ResponseHelper.create(null, 10011, "目前处于禁止预约状态,解禁时间为:\t" + end_time.split(" ")[0]);
            } else {
                blackListDao.deleteBlackUser(user_id);
            }
        }

        Map<String, String> paraMap = new HashMap();
        AppointmentInfo appointmentInfo = new AppointmentInfo();
        String reserve_id = GetUUID.getUUID();
        String create_time = TimeOpt.getCurrentTime();

        appointmentInfo.setReserve_id(reserve_id);
        appointmentInfo.setUser_id(user_id);
//        reserveInfo.setIllness_content(jsonObject.getString("illness_content"));
        appointmentInfo.setDep_id(jsonObject.getString("dep_id"));
        appointmentInfo.setStaff_id(jsonObject.getString("staff_id"));
        appointmentInfo.setReserve_status("SUCCESS");
        appointmentInfo.setCreate_time(create_time);
        int flag = jsonObject.getInteger("time");

        ResponseV2 responseV2 = null;

        //上午
        if (flag == 1) {
            paraMap.put("staff_id", jsonObject.getString("staff_id"));
            paraMap.put("work_time1", reserve_date + " 08:00:00");
            paraMap.put("work_time2", reserve_date + " 12:00:00");
            int reserve_num = appointmentDao.getReserveNum(paraMap);
            if (reserve_num == 0) {
                String reserve_time = reserve_date + morningTimes[0];
                appointmentInfo.setReserve_time(reserve_time);
                appointmentDao.addReserve(appointmentInfo);
                responseV2 = ResponseHelper.create(null, 200, "您已预约成功,时间为: "+reserve_date + morningTimes[0]);
            } else if (reserve_num == 1) {
                String reserve_time = reserve_date + morningTimes[1];
                appointmentInfo.setReserve_time(reserve_time);
                appointmentDao.addReserve(appointmentInfo);
                responseV2 = ResponseHelper.create(null, 200, "您已预约成功,时间为: " +reserve_date+ morningTimes[1]);
            } else if (reserve_num == 2) {
                String reserve_time = reserve_date + morningTimes[2];
                appointmentInfo.setReserve_time(reserve_time);
                appointmentDao.addReserve(appointmentInfo);
                responseV2 = ResponseHelper.create(null, 200, "您已预约成功,时间为: " +reserve_date+ morningTimes[2]);
            } else if (reserve_num == 3) {
                responseV2 = ResponseHelper.create("", 1007, "该医生这天上午预约已满,请换个时间预约");
            }
        } else if (flag == 2) {
            paraMap.put("staff_id", appointmentInfo.getStaff_id());
            paraMap.put("work_time1", reserve_date + " 13:00:00");
            paraMap.put("work_time2", reserve_date + " 16:00:00");
            int reserve_num = appointmentDao.getReserveNum(paraMap);
            if (reserve_num == 0) {
                String reserve_time = reserve_date + afternoonTimes[0];
                appointmentInfo.setReserve_time(reserve_time);
                appointmentDao.addReserve(appointmentInfo);
                responseV2 = ResponseHelper.create(null, 200, "您已预约成功,时间为: " +reserve_date+ afternoonTimes[0]);
            } else if (reserve_num == 1) {
                String reserve_time = reserve_date + afternoonTimes[1];
                appointmentInfo.setReserve_time(reserve_time);
                appointmentDao.addReserve(appointmentInfo);
                responseV2 = ResponseHelper.create(null, 200, "您已预约成功,时间为: "+reserve_date + afternoonTimes[1]);
            } else if (reserve_num == 2) {
                String reserve_time = reserve_date + afternoonTimes[2];
                appointmentInfo.setReserve_time(reserve_time);
                appointmentDao.addReserve(appointmentInfo);
                responseV2 = ResponseHelper.create(null, 200, "您已预约成功,时间为: "+reserve_date + afternoonTimes[2]);
            } else if (reserve_num == 3) {
                responseV2 = ResponseHelper.create("", 1007, "该医生这天下午预约已满,请换个时间预约");
            }
        }
        return responseV2;
    }

    //查看一段时间内的历史预约记录
    @RequestMapping(value = "/reserve/history")
    @ResponseBody
    public String returnReserveById(@RequestParam Map<String,Object> map) {

        System.out.println(map);
        String user_id =(String) map.get("user_id");
        HashMap hashMap = new HashMap();
        hashMap.put("user_id", user_id);
        List<AppointmentHistoryInfo> reserveList = appointmentDao.searchReserveHistory(hashMap);

        JSONObject jobj = new JSONObject();
        jobj.put("code",0);
        jobj.put("msg","");
        jobj.put("count",reserveList.size());
        jobj.put("data",reserveList);
//        System.out.println(jobj.toString());
        return jobj.toString();
//        return ResponseHelper.create(reserveList, 200, "历史预约信息查询成功");
    }

    //一条预约详情
    @RequestMapping(value = "/reserve/detail", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 reserveInfoDetail(@RequestBody JSONObject para) {
        String reserve_id = para.getString("reserve_id");
        AppointmentInfo appointmentInfo = appointmentDao.reserveInfoDetail(reserve_id);
        return ResponseHelper.create(appointmentInfo, 200, "详细预约信息查询成功");
    }

    //取消预约消息
    @RequestMapping(value = "/reserve/cancel",method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 cancelReserve(@RequestBody JSONObject para){
        String reserve_id = para.getString("cancel_id_visit");
        String user_id = para.getString("user_id");
        Map<String,String> paraMap = new HashMap<>();
        paraMap.put("user_id",user_id);
        paraMap.put("reserve_id",reserve_id);
        appointmentDao.deleteReserve(paraMap);
        return ResponseHelper.create(null, 200, "取消预约信息成功,请重新预约");
    }

    //查询医生上班情况
    @RequestMapping(value = "/reserve/depworkstatus",method = RequestMethod.GET)
    @ResponseBody
    public ResponseV2 getDoctorWorkTime(@RequestParam Map<String,Object> para) {

        String dep_id =(String) para.get("dep_id");
        String type = (String)para.get("type");
        System.out.println(dep_id+"  "+type);

        List<DoctorInfo> doctorInfoList;

        Map<String,String> paraMap = new HashMap<>();
        String futureTime = "";
        if(type != null && type.equals("today")){
           futureTime = TimeOpt.getFetureDate(1).split(" ")[0];
            paraMap.put("dep_id",dep_id);
            paraMap.put("future_time",futureTime);
            doctorInfoList = appointmentDao.getWorkTimeToday(paraMap);
        }else {
            futureTime = TimeOpt.getFetureDate(7).split(" ")[0];
            paraMap.put("dep_id",dep_id);
            paraMap.put("future_time",futureTime);
            doctorInfoList = appointmentDao.getWorkTimeSevenDays(paraMap);
        }

        for (DoctorInfo doctorInfo: doctorInfoList) {
            if(doctorInfo.getWork_status().equals("ON")) {
                String staff_id = doctorInfo.getDoctor_id();
                String reserve_date = doctorInfo.getWork_time().split(" ")[0];
                doctorInfo.setWork_time(reserve_date);
                paraMap.put("staff_id", staff_id);
                paraMap.put("work_time1", reserve_date + " 08:00:00");
                paraMap.put("work_time2", reserve_date + " 12:00:00");
                int am_num = appointmentDao.getReserveNum(paraMap);
                paraMap.put("work_time1", reserve_date + " 13:00:00");
                paraMap.put("work_time2", reserve_date + " 16:00:00");
                int pm_num = appointmentDao.getReserveNum(paraMap);
                System.out.println(am_num + "   " + pm_num);
                if (am_num == 3) {
                    doctorInfo.setAm(0);
                } else {
                    doctorInfo.setAm(1);
                }

                if (pm_num == 3) {
                    doctorInfo.setPm(0);
                } else {
                    doctorInfo.setPm(1);
                }
            }
        }
        System.out.println(doctorInfoList);
        return ResponseHelper.create(doctorInfoList, 200, "医生上班情况查询成功");
    }

    public static void main(String[] args) {
        Map<String,Object> map = new HashMap<>();
        map.put("dep_id","123");
        new AppointmentControl().getDoctorWorkTime(map);
    }
}
