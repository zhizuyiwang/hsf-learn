package com.hsf.my.netty.batis.pojo;

import lombok.Data;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Data
public class Configuration {

    //数据源
    private DataSource dataSource;
    //map集合： key:statementId value:MappedStatement
    private Map<String, MappedStatement> mappedStatementMap =
            new HashMap<String, MappedStatement>();
}
