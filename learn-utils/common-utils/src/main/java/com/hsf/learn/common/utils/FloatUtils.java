package com.hsf.learn.common.utils;

/**
 * float 工具类验证
 * @author og 19.11.07
 */
public class FloatUtils {

    public static boolean isEmpty(Float f){
        return null == f;
    }

    public static boolean isNoneZero(Float f){
        return null != f && 0f != f;
    }

    public static boolean isZeroEmpty(Float f){
        return null == f || 0f == f;
    }

    public static void main(String[] args) {
        System.out.println(isNoneZero(0f));
    }
}
