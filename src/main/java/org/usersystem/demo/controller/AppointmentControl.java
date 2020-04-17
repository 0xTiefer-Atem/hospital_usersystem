package org.usersystem.demo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.usersystem.demo.dao.AppointmentDao;
import org.usersystem.demo.opt.*;
import org.usersystem.demo.pojo.AppointmentHistoryInfo;
import org.usersystem.demo.pojo.AppointmentInfo;
import org.usersystem.demo.pojo.DoctorWorkInfo;

import javax.annotation.Resource;
import java.util.*;

@Controller
@CrossOrigin
public class AppointmentControl {

    @Resource
    AppointmentDao appointmentDao;

    private static String[] morningTimes = {"08:00:00","09:00:00","10:00:00"};
    private static String[] afternoonTimes = {"13:00:00","14:00:00","15:00:00"};

    //录入预约信息
    @RequestMapping(value = "/appointment/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 addReserve(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject);
        String userId = jsonObject.getString("userId");//获得用户id
        String type = jsonObject.getString("type");//约上午还是下午
        int num = jsonObject.getInteger("num");//他是第几个预约的
        String staffId = jsonObject.getString("staffId");//要预约的医生的id
        String date = jsonObject.getString("date");//预约的时间

        Map<String, String> paraMap = new HashMap();
        AppointmentInfo appointmentInfo = new AppointmentInfo();
        String appointmentId = GetUUID.getUUID();
        String createTime = TimeOpt.getCurrentTime();

        appointmentInfo.setAppointmentId(appointmentId);
        appointmentInfo.setUserId(userId);
        appointmentInfo.setStaffId(jsonObject.getString("staff_id"));
        appointmentInfo.setStatus("SUCCESS");
        appointmentInfo.setCreateTime(createTime);

        ResponseV2 responseV2 = null;

        if("morning".equals(type)) {
            String appointmentTime = date + morningTimes[num];
            appointmentInfo.setAppointmentTime(appointmentTime);
            appointmentDao.addAppointmentInfo(appointmentInfo);
        }


        //上午
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



    //查询医生7天内预约情况
    @RequestMapping(value = "/get/staffWorkScheduleList",method = RequestMethod.POST)
    @ResponseBody
    public ResponseV2 getDoctorWorkTime(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject.toJSONString());
        String staffId =(String) jsonObject.get("staffId");

        List<String> doctorAppointmentTimeList;//这个医生已经预约的时间

        Map<String,String> paraMap = new HashMap<>();
        String startTime = TimeOpt.getCurrentTime().split(" ")[0];;
        String endTimeTime = TimeOpt.getFetureDate(7).split(" ")[0];
        paraMap.put("staffId",staffId);
        paraMap.put("startTime",startTime);
        paraMap.put("endTimeTime",endTimeTime);
        doctorAppointmentTimeList = appointmentDao.getWorkTimeSevenDays(paraMap);
        System.out.println(doctorAppointmentTimeList);
        Map<String,DoctorWorkInfo> workInfoMap = new HashMap<>();
        for (String appointmentTime: doctorAppointmentTimeList) {
            String date = appointmentTime.split(" ")[0];
            if(workInfoMap.containsKey(date)) {
                DoctorWorkInfo workInfo = workInfoMap.get(date);
                if (TimeOpt.moringOrAfterNoon(appointmentTime) > 0) {
                    int morningNum = workInfo.getMorningNum() + 1;
                    workInfo.setMorningNum(morningNum);
                } else {
                    int afternoonNum = workInfo.getAfternoonNum() + 1;
                    workInfo.setAfternoonNum(afternoonNum);
                }
                workInfoMap.replace(date, workInfo);
            }else {
                DoctorWorkInfo workInfo = new DoctorWorkInfo();
                workInfo.setAppointmentTime(date);
                if (TimeOpt.moringOrAfterNoon(appointmentTime) > 0) {
                    int morningNum = workInfo.getMorningNum() + 1;
                    workInfo.setMorningNum(morningNum);
                } else {
                    int afternoonNum = workInfo.getAfternoonNum() + 1;
                    workInfo.setAfternoonNum(afternoonNum);
                }
                workInfoMap.put(date, workInfo);
            }
        }


        List<DoctorWorkInfo> workList = new ArrayList<>();

        for (Map.Entry<String,DoctorWorkInfo> entry: workInfoMap.entrySet()) {
            workList.add(entry.getValue());
        }

        for (int i = 0; i < 6; i++) {
            String date = TimeOpt.getFetureDate(i).split(" ")[0];
            int size = workList.size();
            boolean flag = false;
            for (int j = 0; j < size; j++) {
                if(workList.get(j).getAppointmentTime().equals(date)) {
                    flag = true;
                }
            }
            if(!flag) {
                DoctorWorkInfo workInfo = new DoctorWorkInfo();
                workInfo.setAppointmentTime(date);
                workList.add(workInfo);
            }
        }

        System.out.println(workList);


        return ResponseHelper.create(workList, 200, "医生上班情况查询成功");
    }

    public static void main(String[] args) {

    }
}
