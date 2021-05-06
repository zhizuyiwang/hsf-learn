package com.hsf.my.netty.batis.config;

import com.hsf.my.netty.batis.utils.ParameterMapping;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BoundSql {

    //解析过后的sql语句
    private String sqlText;

    //解析出来的参数
    private List<ParameterMapping> parameterMappingList =
            new ArrayList<ParameterMapping>();

    public BoundSql(String sqlText, List<ParameterMapping> parameterMappingList){
        this.sqlText = sqlText;
        this.parameterMappingList = parameterMappingList;
    }

}
