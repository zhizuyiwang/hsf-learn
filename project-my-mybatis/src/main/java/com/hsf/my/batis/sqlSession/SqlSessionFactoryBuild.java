package com.hsf.my.batis.sqlSession;

import com.hsf.my.batis.config.XmlConfigureBuilder;
import com.hsf.my.batis.pojo.Configuration;

import java.io.InputStream;


public class SqlSessionFactoryBuild {

    private Configuration configuration;

    public SqlSessionFactoryBuild(){
        configuration = new Configuration();
    }

    public SqlSessionFactory build(InputStream inputStream){

        try {
            //1.解析配置文件，封装Configuration
            XmlConfigureBuilder xmlConfigureBuilder = new XmlConfigureBuilder(configuration);
            Configuration configuration = xmlConfigureBuilder.parseConfiguration(inputStream);

            //2.创建 sqlSessionFactory
            DefaultSqlSessionFactory sqlSessionFactory =
                    new DefaultSqlSessionFactory(configuration);

            return sqlSessionFactory;
        }catch (Exception e){
            throw new RuntimeException("出现错误，请检查");
        }

    }
}
