package com.hsf.learn.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * fast json util
 * @author ogz 19.1.9
 */
public class FastJsonUtils {

    /**
     * bean to json 过滤null的数据
     * @param vo prototype
     * @return str
     */
    public static String beanToJson(Object vo){
        return validateEmpty(vo) ? JSONObject.toJSON(vo).toString() : null;
    }

    /**
     * bean to json all include null
     * @param vo prototype
     * @return str
     */
    public static String beanToJsonAll(Object vo) { return validateEmpty(vo) ? JSON.toJSONString(vo ,
            SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue) : null;  }

    /**
     * json to bean
     * @param json base
     * @param clz class
     * @param <T> return type
     * @return bean
     */
    public static <T> T jsonToBean(String json,Class<T> clz){

        return validateEmpty(json)? JSONObject.parseObject(json,clz) : null;
    }

    /**
     * 支持泛型转换
     * 比如 服务于服务之间通过mq进行交互，消息的约定规则字段大部分是固定的;而针对与业务数据是不同的，一般会采用泛型
     * @param json base
     * @param reference 泛型
     * @param <T> type
     * @return Obj
     */
//    public static <T> ReceiveResultVo<T> jsonToBean(String json, TypeReference<ReceiveResultVo<T>> reference){
//        return validateEmpty(json)? JSON.parseObject(json, reference) : null;
//    }


    /**
     * 批量序列化
     * @param json json
     * @param reference 泛型
     * @param <T> type
     * @return ReceiveResultListVo
     */
//    public static <T> ReceiveResultListVo<T> jsonToBeanList(String json, TypeReference<ReceiveResultListVo<T>> reference){
//        return validateEmpty(json)? JSON.parseObject(json, reference) : null;
//    }

    /**
     * list to json
     * @param vo base
     * @return str
     */
    public static String arrayToJson(List vo){
        return validateListEmpty(vo)? JSONArray.toJSON(vo).toString() : null;
    }

    /**
     * json to List
     * @param json base
     * @param clz class
     * @param <T> type
     * @return List<T>
     */
    public static <T> List<T> jsonToArray(String json, Class<T> clz){
        return validateEmpty(json)? JSONArray.parseArray(json,clz) : null;
    }

    /**
     * 私有化空校验
     * @param vo base
     * @return boolean
     */
    private static boolean validateEmpty(Object vo){
        return null == vo? Boolean.FALSE : Boolean.TRUE;
    }

    /**
     * 校验空集合
     * @param vo base
     * @return boolean
     */
    private static boolean validateListEmpty(List vo){return null == vo || vo.size() <=0? Boolean.FALSE : Boolean.TRUE;}


    public static void main(String[] args) {
        String content = "{\"token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImJzaGJramQiLCJleHAiOjE1NTA2NzQzNDV9.swEkcV5q1SbmYT6_NIcxdHClD-hu-5wiK6D61dRcOdk\", \"code\": 200}";

        Map<String,String> res = jsonToBean(content,Map.class);
        System.out.println(res.get("token"));

    }

}
