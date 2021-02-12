package com.hsf.learn.common.utils;


import java.util.List;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * @author ogz 19.5.22
 */
public class ValidateUtils {

    /**
     * null 校验
     * @param vl val
     * @return boolean
     */
    public static boolean empty(String vl){
        return (null == vl || "".equals(vl) || "".equals(vl.trim()) || vl.length() == 0);
    }

    public static boolean empty(List vl){
        return null == vl || vl.size() == 0;
    }

    public static boolean empty(Object obj){ return  null == obj;}

    public static boolean empty(Long vl) {return  null == vl;}

    public static boolean empty(Integer vl) {return null == vl;}

    public static boolean empty(Object[] vl) {return  null == vl || vl.length == 0;}

    /**
     * 校验是否是纯数字字符串
     * @return boolean
     */
    public static boolean numberString(String vl){
        Pattern pattern = Pattern.compile("[0-9]*");
        return !ValidateUtils.empty(vl) && pattern.matcher(vl).matches();
    }

    /**
     * 校验纯数字字符串 + 长度
     * @param vl str
     * @param len 长度
     * @return boolean
     */
    public static boolean numberAndLength(String vl, int len) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return !ValidateUtils.empty(vl) && pattern.matcher(vl).matches() && vl.length() == len;
    }

    public static void main(String[] args) {
        System.out.println(numberString("123213"));
    }
}
