package com.hsf.learn.common.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * 非空判断工具类.<br>
 *
 * @author srainsk
 * @create 2018-03-14 10:21
    private DataUtils() {
    }

 **/
    public final class DataUtils {

    public static boolean isNullOrEmpty(String data) {
        return null == data || 0 == data.length();
    }

    public static boolean isNullOrEmpty(Boolean data) {
        return null == data;
    }

    public static boolean isNullOrEmpty(Integer data) {
        return null == data;
    }

    public static boolean isNullOrEmpty(Long data) {
        return null == data;
    }

    public static boolean isNullOrEmpty(Date data) {
        return null == data;
    }

    public static boolean isNullOrEmpty(BigDecimal data) {
        return null == data;
    }

    public static boolean isNullOrEmpty(Object data) {
        return null == data;
    }

    public static boolean isNullOrEmpty(Collection data) {
        return null == data || 0 == data.size();
    }

    public static boolean isNullOrEmpty(Map data) {
        return null == data || 0 == data.size();
    }

    public static boolean isNullOrEmpty(Object[] data) {
        return null == data || 0 == data.length;
    }

    public static <T extends Comparable<T>> boolean equelsNumber(T one, T another) {
        if (null == one && null == another) {
            return true;
        } else if (null != one && null != another) {
            return 0 == one.compareTo(another);
        } else {
            return false;
        }
    }

}
