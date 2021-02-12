package com.hsf.learn.common.utils.json;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 复杂的json串转换成对象
 */
public class JsonHandle {

    public static void main(String[] args) {
//        String json="{\n" +
//                "    \"code\": \"0000\",\n" +
//                "    \"msg\": \"success\",\n" +
//                "    \"data\": {\n" +
//                "        \"teamInfo\": {\n" +
//                "            \"code\": \"T0002\",\n" +
//                "            \"name\": \"倍肯小组\"\n" +
//                "        }\n" +
//                "    }\n" +
//                "}";
//        BaseBean baseBean = JSONObject.parseObject(json, BaseBean.class);
//        Object next = baseBean.getData().values().iterator().next();
//        DeptBean deptBean = JSONObject.parseObject(JSONObject.toJSONString(next), DeptBean.class);
//        System.out.println(deptBean.getCode());

//
//        String json2 = "{\n" +
//                "    \"code\": \"0000\",\n" +
//                "    \"msg\": \"success\",\n" +
//                "    \"data\": {\n" +
//                "        \"teamInfo\": [{\n" +
//                "            \"code\": \"T0002\",\n" +
//                "            \"name\": \"倍肯小组\"\n" +
//                "        },\n" +
//                "{\n" +
//                "            \"code\": \"T0002\",\n" +
//                "            \"name\": \"倍肯小组\"\n" +
//                "        },\n" +
//                "{\n" +
//                "            \"code\": \"T0002\",\n" +
//                "            \"name\": \"倍肯小组\"\n" +
//                "        }]\n" +
//                "    }\n" +
//                "}";
//
//        BaseBean baseBean = JSONObject.parseObject(json2, BaseBean.class);
//        Object next = baseBean.getData().values().iterator().next();
//        List<DeptBean> deptBeans = JSONObject.parseArray(JSONObject.toJSONString(next), DeptBean.class);
//        System.out.println(deptBeans.size());


        String json3="{\n" +
                "    \"code\": \"0000\",\n" +
                "    \"msg\": \"success\",\n" +
                "    \"data\": {\n" +
                "        \"orgList\": {\n" +
                "            \"records\": [\n" +
                "                {\n" +
                "                    \"code\": \"C0002\",\n" +
                "                    \"name\": \"溧阳水电2\"\n" +
                "                },\n" +
                "               {\n" +
                "                    \"code\": \"C0002\",\n" +
                "                    \"name\": \"溧阳水电2\"\n" +
                "                },\n" +
                "                 {\n" +
                "                    \"code\": \"C0002\",\n" +
                "                    \"name\": \"溧阳水电2\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"size\": 10\n" +
                "        }\n" +
                "    }\n" +
                "}";

        BaseBean baseBean = JSONObject.parseObject(json3, BaseBean.class);
        Object next = baseBean.getData().values().iterator().next();
        BaseListBean<DeptBean> baseListBean = JSONObject.parseObject(JSONObject.toJSONString(next), BaseListBean.class);
        ArrayList<DeptBean> deptBeans = new ArrayList<>(Arrays.asList(baseListBean.getRecords()));
        System.out.println(deptBeans.size());

    }
}
