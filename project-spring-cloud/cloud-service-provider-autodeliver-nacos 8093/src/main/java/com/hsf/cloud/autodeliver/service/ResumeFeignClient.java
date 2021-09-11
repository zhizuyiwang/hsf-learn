package com.hsf.cloud.autodeliver.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// 原来：http://cloud-service-resume/resume/openstate/ + userId;
// @FeignClient表明当前类是一个Feign客户端，value指定该客户端要请求的服务名称（登记到注册中心上的服务提供者的服务名称）
// 使⽤fallback的时候，类上的@RequestMapping的url前缀限定，改成配置在@FeignClient的path属性中
@FeignClient(name = "cloud-service-resume", fallback = ResumeFallback.class, path = "/resume")
//@RequestMapping("/resume")
public interface ResumeFeignClient {
    @GetMapping("/openstate/{userId}")
    Integer findResumeOpenStateByFeign(@PathVariable("userId") Integer userId);
}
