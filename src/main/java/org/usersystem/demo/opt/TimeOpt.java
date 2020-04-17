package org.usersystem.demo.opt;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeOpt {
    private static Date date = new Date();
    private static String formatString = "yyyy-MM-dd HH:mm:ss";
    private static SimpleDateFormat sdf = new SimpleDateFormat(formatString);

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        return sdf.format(date);
    }


    /**
     * 获取未来 第 past 天的日期
     *
     * @param day
     * @return
     */
    public static String getFetureDate(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + day);
        Date today = calendar.getTime();
        String result = sdf.format(today);
        return result;
    }


    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        String result = sdf.format(today);
        return result;
    }


    public static int is_late(String DATE1, String DATE2) {
        try {
            Date dt1 = sdf.parse(DATE1);
            Date dt2 = sdf.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2 后");
                return -1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1 在dt2 前");
                return 1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return 1003;
        }
    }


    public static int moringOrAfterNoon(String date) {
        try {
            Date date1 = sdf.parse(date);
            int hour = date1.getHours();
            if (hour >= 8 && hour <= 11) {
                return 1;//代表上午
            } else if (hour >= 13 && hour <= 16) {
                return -1;//代表下午
            } else {
                return 0;//其余时间不能预约
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 1003;
        }
    }


    /**
     * 比较两个日期之间的大小
     *
     * @param date1
     * @param date2
     * @return 前者大于后者返回true 反之false
     */
    public static boolean compareDate(String date1, String date2) {

        Date d1 = null;
        Date d2 = null;
        try {
            d1 = sdf.parse(date1);
            d2 = sdf.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);

        int result = c1.compareTo(c2);
        if (result >= 0)
            return true;
        else
            return false;
    }

}
