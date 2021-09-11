package com.hsf.cloud.autodeliver.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hsf.cloud.autodeliver.config.SentinelHandleClass;
import com.hsf.cloud.autodeliver.service.ResumeFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/autodeliver")
public class AutoDeliverController {
    @Autowired
    ResumeFeignClient resumeFeignClient;

    @GetMapping("/checkStateByFeign/{userId}")
    @SentinelResource(value = "findResumeOpenStateByFeignAndSentinel",blockHandlerClass = SentinelHandleClass.class,
    blockHandler = "findResumeOpenStateByRibbonTimeoutHystrix", fallbackClass = SentinelHandleClass.class,
    fallback = "sentinelHandle")
    public Integer findResumeOpenStateByRibbonTimeoutHystrix(@PathVariable Integer userId){
       return resumeFeignClient.findResumeOpenStateByFeign(userId);
    }
    @GetMapping("/register")
    public String register(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(new Date())+"===注册成功");
        return "注册成功";
    }
    @GetMapping("/validate")
    public String validate(){
        return "验证";
    }


}
