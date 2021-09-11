package com.hsf.boot.test.controller;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {

    // 获取当前时间
    public static String getCurrentDateTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(c.getTime());
//        DebugLog.e(time);
        return time;
    }
    // 获取当前日期
    public static String getCurrentDate() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(c.getTime());
        return time;
    }
    public static String discardMillisecond(String time){
        try {
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
            SimpleDateFormat sdFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date parse = sdFormat.parse(time);

            String myTime = sdFormat2.format(parse);

            return myTime;
        }catch (Exception e){

        }
        return time;
    }
    public static int[] getYMDArray(String datetime, String splite) {
        int[] date = {0, 0, 0, 0, 0};
        if (datetime != null && datetime.length() > 0) {
            String[] dates = datetime.split(splite);
            int position = 0;
            for (String temp : dates) {
                date[position] = Integer.valueOf(temp);
                position++;
            }
        }
        return date;
    }

    /**
     * 将当前时间戳转化为标准时间函数
     *
     * @return
     */
    public static String getTime(String time1) {

        int timestamp = Integer.parseInt(time1);

        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        String time = null;
        try {
            String str = sdf.format(new Timestamp(intToLong(timestamp)));
            time = str.substring(11, 16);
            String month = str.substring(5, 7);
            String day = str.substring(8, 10);
            time = getDate(month, day) + time;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return time;
    }

    public static String getTime(int timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = null;
        try {
            String str = sdf.format(new Timestamp(intToLong(timestamp)));
            time = str.substring(11, 16);

            String month = str.substring(5, 7);
            String day = str.substring(8, 10);
            time = getDate(month, day) + time;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return time;
    }

    public static String getHMS(long timestamp) {
        timestamp -= TimeZone.getDefault().getRawOffset();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = null;
        try {
            return sdf.format(new Date(timestamp));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return time;
    }

    public static String getMS(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(timestamp);
        return sdf.format(date);
    }

    /**
     * 将当前时间戳转化为标准时间函数
     *
     * @return
     */
    public static String getHMS(String time) {

        long timestamp = Long.parseLong(time);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            String str = sdf.format(new Timestamp(timestamp));
            return str;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return time;
    }

    // java Timestamp构造函数需传入Long型
    public static long intToLong(int i) {
        long result = (long) i;
        result *= 1000;
        return result;
    }

    public static String getDate(String month, String day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 24小时制
        Date d = new Date();
        ;
        String str = sdf.format(d);
        @SuppressWarnings("unused")
        String nowmonth = str.substring(5, 7);
        String nowday = str.substring(8, 10);
        String result = null;

        int temp = Integer.parseInt(nowday) - Integer.parseInt(day);
        switch (temp) {
            case 0:
                result = "今天";
                break;
            case 1:
                result = "昨天";
                break;
            case 2:
                result = "前天";
                break;
            default:
                StringBuilder sb = new StringBuilder();
                sb.append(Integer.parseInt(month) + "月");
                sb.append(Integer.parseInt(day) + "日");
                result = sb.toString();
                break;
        }
        return result;
    }

    /* 将字符串转为时间戳 */
    public static String getTimeToStamp(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒",
                Locale.CHINA);
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String tmptime = String.valueOf(date.getTime()).substring(0, 10);

        return tmptime;
    }

    public static String getYMD(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(timestamp));
    }

    public static String getDate(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return sdf.format(new Date(timestamp));
    }

    public static String getTimestamp() {
        long time = System.currentTimeMillis() / 1000;
        return String.valueOf(time);
    }

    public static long getMillis(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = 0;
        try {
            time = sdf.parse(data).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 抓拍指令,实时视频,触发时间  hh:mm
     * @return
     */
    public static String getSamplingTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        String time = format.format(calendar.getTime());
        return time;
    }


    /**
     * 把1-9的数字转换为01到09的字符串
     * @param data
     * @return
     */
    public static String intToDateString(int data){
        String strDate;
        switch (data){
            case 0:
                strDate = "00";
                break;
            case 1:
                strDate = "01";
                break;
            case 2:
                strDate = "02";
                break;
            case 3:
                strDate = "03";
                break;
            case 4:
                strDate = "04";
                break;
            case 5:
                strDate = "05";
                break;
            case 6:
                strDate = "06";
                break;
            case 7:
                strDate = "07";
                break;
            case 8:
                strDate = "08";
                break;
            case 9:
                strDate = "09";
                break;
            default:
                strDate = data + "";
        }

        return strDate;

    }

    /**
     * 把01-09的字符串转换为1到9的数字
     * @return
     */
    public static int stringToDateInt(String strDate){
        int date;
        if("00".equals(strDate)){
            date = 0;
        } else if("01".equals(strDate)){
            date = 1;
        }else if("02".equals(strDate)){
            date = 2;
        }else if("03".equals(strDate)){
            date = 3;
        }else if("04".equals(strDate)){
            date = 4;
        }else if("05".equals(strDate)){
            date = 5;
        }else if("06".equals(strDate)){
            date = 6;
        }else if("07".equals(strDate)){
            date = 7;
        }else if("08".equals(strDate)){
            date = 8;
        }else if("09".equals(strDate)){
            date = 9;
        }else{
            date = Integer.parseInt(strDate);
        }
        return date;
    }

    /**
     * @return 根据输入的时间判断设备是否超过半个小时没更新
     */
    public static boolean deviceIsOffLine(String updateTime){
        if(null == updateTime || updateTime.equals("")){
            return false;
        }
        long currentLongTime = new Date().getTime();
        long updateLongTime = 0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date updateDateTime = simpleDateFormat.parse(updateTime);
            updateLongTime = updateDateTime.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return currentLongTime >= updateLongTime + 0.5 * 60 * 60 * 1000;
    }

    public static int[] getNumberDate(String dateTime) {
        int[] date = new int[3];
        String[] dateTimeSplit = dateTime.split("-");
        date[0] = Integer.parseInt(dateTimeSplit[0]);
        date[1] = stringToDateInt(dateTimeSplit[1]);
        date[2] = stringToDateInt(dateTimeSplit[2]);
        return date;
    }


    public static boolean judgeIsValid(String startTime, String endTime) {

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        try {
            Date start = timeFormat.parse(startTime);//开始时间
            Date end = timeFormat.parse(endTime);//结束时间
            if(end.getTime() > start.getTime()){
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean isOneHour(String samplingTime) {
        long millis = getMillis(samplingTime);
        long time = new Date().getTime();
        if(time - millis < 60 * 60 * 1000){
            return true;
        }
        return false;
    }




    public static String getPrevOrNextDate(String startTime, int number) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            String today = startTime.split(" ")[0];
            Date todayDate = simpleDateFormat.parse(today);
            calendar.setTime(todayDate);
            calendar.add(Calendar.DATE, number);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        return  simpleDateFormat.format(calendar.getTime());
    }


}