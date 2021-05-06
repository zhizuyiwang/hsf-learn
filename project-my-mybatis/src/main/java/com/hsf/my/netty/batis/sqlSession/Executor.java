package com.hsf.my.netty.batis.sqlSession;

import com.hsf.my.netty.batis.pojo.Configuration;
import com.hsf.my.netty.batis.pojo.MappedStatement;

import java.sql.SQLException;
import java.util.List;

public interface Executor {

    <E> List<E> query(Configuration configuration,
                      MappedStatement mappedStatement, Object[] params) throws Exception;

    void close() throws SQLException;
}
