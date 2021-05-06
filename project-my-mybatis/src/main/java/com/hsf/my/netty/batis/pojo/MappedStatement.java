package com.hsf.my.netty.batis.pojo;

import lombok.Data;

@Data
public class MappedStatement {

    //id
    private String id;
    //sql语句
    private String sql;
    //输入参数类型
    private Class<?> parameterType;
    //输出结果参数类型
    private Class<?> resultType;

}
