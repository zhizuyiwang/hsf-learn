package com.hsf.cloud.resume.controller;

import com.hsf.cloud.resume.service.IResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resume")
@RefreshScope//通过 Spring Cloud 原⽣注解  @RefreshScope 实现配置⾃动更新
public class ResumeController {
    //将配置文件写到bootstrap.yml中，如将nacos配置写在application.yml中，
    // nacos在初始化的时候是读取不到配置的。
    @Value("${server.port}")
    private int port;
    @Value("${mysql.msg}")
    private String msg;
    @Value("${abc.msg}")
    private String abc_msg;
    @Value("${bcd.msg}")
    private String bcd_msg;

    @Autowired
    private IResumeService resumeService;
    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeByUserId(@PathVariable Long userId){
        return resumeService.findDefaultResumeByUserId(userId).getIsOpenResume();
    }
    @GetMapping("/config")
    public void testConfig(){
        System.out.println("port=="+port+";msg=="+msg+";abc==="+abc_msg+";bcd=="+bcd_msg);
    }
}
