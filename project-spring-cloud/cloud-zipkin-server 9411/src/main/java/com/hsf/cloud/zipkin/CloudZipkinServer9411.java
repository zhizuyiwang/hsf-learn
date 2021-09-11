package com.hsf.cloud.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import zipkin2.server.internal.EnableZipkinServer;

import javax.sql.DataSource;

@SpringBootApplication
@EnableZipkinServer
public class CloudZipkinServer9411 {

    public static void main(String[] args) {
        SpringApplication.run(CloudZipkinServer9411.class, args);

    }

    @Bean
    public PlatformTransactionManager txManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
