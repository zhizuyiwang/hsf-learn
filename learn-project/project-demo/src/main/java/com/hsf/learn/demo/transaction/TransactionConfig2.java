package com.hsf.learn.demo.transaction;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 手动选择生成需要类型的事务管理器
 */
@EnableTransactionManagement
@Configuration
public class TransactionConfig2 {

    /**
     *在Spring容器中，我们手工注解@Bean 将被优先加载，框架不会重新实例化
     * 其他的 PlatformTransactionManager 实现类。
     *
     * 然后在Service中，被 @Transactional 注解的方法，将支持事务。如果注解在类上，
     * 则整个类的所有方法都默认支持事务。
     *
     * @param dataSource
     * @return
     */
    @Bean
    public PlatformTransactionManager getTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public Object getObj(@Qualifier("getTransactionManager") PlatformTransactionManager platformTransactionManager){
        platformTransactionManager.getClass().getName();
        return new Object();
    }



}
