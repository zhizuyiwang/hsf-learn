package com.hsf.my.netty.batis.sqlSession;

import java.sql.SQLException;
import java.util.List;

public interface SqlSession {
    <E> E selectOne(String statementId, Object... params) throws Exception;

    <E> List<E> selectList(String statementId, Object... params) throws Exception;

    void close() throws SQLException;

    public <T> T getMapper(Class<?> mapperClass);
}
