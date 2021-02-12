package com.hsf.learn.common.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * jodaTime
 *
 * @author ogz 19.5.19
 */
public class JodaTimeUtils {


    /**
     * 提前预置的数据格式
     */
    private static final String   YM        = "yyyy-MM";
    private static final String   YMD       = "yyyy-MM-dd";
    private static final String   YMDHMS    = "yyyy-MM-dd HH:mm:ss";
    private static final String   NO_YMDHMS = "yyyyMMddHHmmss";
    private static final String   YMD_ONLY  = "yyyyMMdd";
    private static final int      Frequency = 5;
    private static final Long     Seperator = 300000L;

    private static final DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    private static final long     EXPIRE_TIME = 30*24*60*1000;

    /**
     * 获取当前的日期
     * @return
     */
    public static String curDate(){
        return DateTime.now().toString(YMD);
    }

    public static String curDateOnly(){ return DateTime.now().toString(YMD_ONLY);}

    public static String minusMin(int min){
        return DateTime.now().minusMinutes(min).toString(YMDHMS);
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String curTime(){
        return DateTime.now().toString(YMDHMS);
    }

    public static String lastTime(int min){
        return DateTime.now().minusMinutes(min).toString(YMDHMS);
    }

    public static String curTimeStr(){
        return DateTime.now().toString(NO_YMDHMS);
    }

    public static String curTime(DateTime now){
        return now.toString(YMDHMS);
    }


    public static String toYMD(DateTime dateTime){
        return dateTime.toString(YMD);
    }

    /**
     * 校验Token
     * @param loginMillis
     * @return
     */
    public static boolean validateToken(long loginMillis){
        return (DateTime.now().getMillis() - loginMillis) < EXPIRE_TIME;
    }

    /**
     * 获取毫秒
     * @return long
     */
    public static long  curMillis(){
        return DateTime.now().getMillis();
    }

    public static long curMillis(DateTime now){
        return now.getMillis();
    }

    /**
     * 字符串日期格式化
     * @return DateTime
     */
    public static DateTime formatTime(String str){
        return DateTime.parse(str, format);
    }

    /**
     * ymdhms to dateTime
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param min
     * @param sec
     * @return
     */
    public static DateTime convertYMDHMSToDateTime(int year, int month, int day, int hour, int min, int sec){
        return new DateTime(year + 2000, month, day, hour, min, sec);
    }

    /**
     * 毫秒值转日期
     * @param times 毫秒
     * @return DateTime
     */
    public static DateTime convertTimesToDateTime(long times){
        return new DateTime(times);
    }

    /**
     * 返回时间字符串
     * @param times
     * @return
     */
    public static Date convertTimesToDate(long times){return new DateTime(times).toDate();}

    public static String convertTimesToDateString(long times){return new DateTime(times).toString(YMDHMS);}

    public static void main(String[] args) {
        DateTime now = DateTime.now();

        System.out.println(now.getMillis() - EXPIRE_TIME);

        System.out.println(JodaTimeUtils.curTimeStr());
    }
}
