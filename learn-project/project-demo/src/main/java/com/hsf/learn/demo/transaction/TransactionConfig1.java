package com.hsf.learn.demo.transaction;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//根据依赖自定生成相应类型的事务管理器
@EnableTransactionManagement
@Configuration
public class TransactionConfig1 {
    /***
     * 关于事务管理器，不管是JPA还是JDBC等都实现自接口 PlatformTransactionManager
     * 如果你添加的是 spring-boot-starter-jdbc 依赖，框架会默认注入 DataSourceTransactionManager 实例。
     * 如果你添加的是 spring-boot-starter-data-jpa 依赖，框架会默认注入 JpaTransactionManager 实例。
     */

//    @Bean
//    public Object getTransactionBean(@Qualifier("transactionManager") PlatformTransactionManager platformTransactionManager){
//        platformTransactionManager.getClass().getName();
//        return new Object();
//    }
    public static void main(String[] args) {
        String test = "kkk23:333";
        System.out.println(test.substring(0,test.indexOf(":")));
        if(test.indexOf(":") != test.length()-1){
            System.out.println(test.substring(test.indexOf(":") + 1));

        }


    }

}
