package com.hsf.cloud.autodeliver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/autodeliver")
public class AutoDeliverController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Integer userId){
        //1、获取Eureka中注册的指定服务的服务实例列表
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-service-resume");
        //2、在服务实例列表中获取一个实例，这里不考虑负载均衡
        ServiceInstance serviceInstance = instances.get(0);
        //3、根据服务实例获取该实例的host和port
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        //4、拼接api
        String url = "http://" + host + ":" + port + "/resume/openstate/" + userId;
        System.out.println("url:"+url);
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }
}
