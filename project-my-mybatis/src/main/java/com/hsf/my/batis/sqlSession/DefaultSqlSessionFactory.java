package com.hsf.my.batis.sqlSession;

import com.hsf.my.batis.pojo.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory{
    private Configuration configuration;
    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession(){
        return new DefaultSqlSession(configuration);
    }
}
