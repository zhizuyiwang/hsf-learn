package com.heima.article.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Mysql初始化扫描配置类
 */
@Configuration
@ComponentScan("com.heima.common.mysql.core")
public class MysqlConfig {
}
