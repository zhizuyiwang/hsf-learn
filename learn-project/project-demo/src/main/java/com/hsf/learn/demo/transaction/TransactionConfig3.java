package com.hsf.learn.demo.transaction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * 同一个工程中存在多个事务管理器
 */
@EnableTransactionManagement
@Configuration
public class TransactionConfig3 implements TransactionManagementConfigurer {

//    @Resource(name="txManager2")
    private PlatformTransactionManager txManager2;


    // 创建事务管理器1
    @Bean(name = "txManager1")
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

//    // 创建事务管理器2
//    @Bean(name = "txManager2")
//    public PlatformTransactionManager txManager2(EntityManagerFactory factory) {
//        return new JpaTransactionManager(factory);
//    }

    @Override
    public TransactionManager annotationDrivenTransactionManager() {
        return null;
    }


}
