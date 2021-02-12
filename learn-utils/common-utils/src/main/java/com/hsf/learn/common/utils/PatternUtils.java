package com.hsf.learn.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Pattern;

/**
 * 正则工具类
 * @author og 19.0626
 */
public class PatternUtils {

    private static final Logger log = LoggerFactory.getLogger(PatternUtils.class);

    /**
     * 日期验证
     * yyyy-mm-dd HH:mm:ss or
     * yyyy-mm-dd
     */
    private static final Pattern P_DATE_DATETIME = Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
    /**
     * hh:mm:ss
     */
    private static final Pattern P_HOUR_MIN_SEC  = Pattern.compile("((((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
    /**
     * hh:mm
     */
    private static final Pattern P_HOUR_MIN      = Pattern.compile("((((0?[0-9])|([1][0-9])|([2][0-4]))\\:([0-5]?[0-9])))?$");
    /**
     * number
     */
    private static final Pattern NUMBER          = Pattern.compile("^-?\\d+(\\.\\d+)?$");

    /**
     * yyyy-mm-dd
     * @return boolean
     */
    public static boolean isDateOrDateTime(String date){
        return StringUtils.isNoneBlank(date) && P_DATE_DATETIME.matcher(date).matches();
    }

    /**
     * HH:mm:ss
     * @param time 时间
     * @return
     */
    public static boolean isHourMinSec(String time){
        return StringUtils.isNoneBlank(time) && P_HOUR_MIN_SEC.matcher(time).matches();
    }

    /**
     * 时分
     * @param time 时间
     * @return boolean
     */
    public static boolean isHourMin(String time){
        return StringUtils.isNoneBlank(time) && P_HOUR_MIN.matcher(time).matches();
    }

    /**
     * 数字验证
     * @param number 数字
     * @return boolean
     */
    public static boolean isNumber(String number){
        return StringUtils.isNoneBlank(number) && NUMBER.matcher(number).matches();
    }

    public static void main(String[] args) {
        String h1 = "23:81";
        String h2 = "23:39:55";
        System.out.println(String.format("%s %s", isHourMin(h1), isHourMin(h2)));
    }

}
