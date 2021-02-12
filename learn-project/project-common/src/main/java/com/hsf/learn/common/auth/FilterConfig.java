package com.hsf.learn.common.auth;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

public class FilterConfig {
    @Resource(name = "authFilter")
    private AuthFilter authFilter;

    @Bean
    public FilterRegistrationBean registerAuthFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(authFilter);
        registration.addUrlPatterns("/*");
        registration.setName("authFilter");
        registration.setOrder(1);  //值越小，Filter越靠前。
        return registration;
    }

    //如果有多个Filter，再写一个public FilterRegistrationBean registerOtherFilter(){...}即可。
}
