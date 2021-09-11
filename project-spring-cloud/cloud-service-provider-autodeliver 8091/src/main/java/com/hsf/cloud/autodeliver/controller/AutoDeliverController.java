package com.hsf.cloud.autodeliver.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    @GetMapping("/checkStateByRibbon/{userId}")
    public Integer findResumeOpenStateByRibbon(@PathVariable Integer userId){
        //1、使用ribbon不需要我们自己获取服务实例然后再选择一个，帮助我们去负载均衡
        String url = "http://cloud-service-resume/resume/openstate/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }


    @HystrixCommand(
            // 线程池标识，要保持唯一，不唯一的话就共用了
            threadPoolKey = "checkStateByRibbonTimeout",
            // 线程池细节属性配置
            threadPoolProperties = {
                    @HystrixProperty(name="coreSize",value = "1"), // 线程数
                    @HystrixProperty(name="maxQueueSize",value="20") // 等待队列长度
            },
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
            }

    )
    @GetMapping("/checkStateByRibbonTimeout/{userId}")
    public Integer findResumeOpenStateByRibbonTimeout(@PathVariable Integer userId){
        //1、使用ribbon不需要我们自己获取服务实例然后再选择一个，帮助我们去负载均衡
        String url = "http://cloud-service-resume/resume/openstate/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }

    @HystrixCommand(
            // 线程池标识，要保持唯⼀，不唯⼀的话就共⽤了
            threadPoolKey = "checkStateByRibbonTimeoutHystrix",
            // 线程池细节属性配置
            threadPoolProperties = {
                    // 线程数
                    @HystrixProperty(name="coreSize",value = "2"),
                    // 等待队列⻓度
                    @HystrixProperty(name="maxQueueSize",value="20")
            },
            //熔断器的细节属性设置
            commandProperties = {
                    @HystrixProperty(
                            name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000"),
                    @HystrixProperty(
                            name = "metrics.rollingStats.timeInMilliseconds",value = "8000"),
                    @HystrixProperty(
                            name = "circuitBreaker.requestVolumeThreshold",value = "2"),
                    @HystrixProperty(
                            name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                    @HystrixProperty(
                            name = "circuitBreaker.sleepWindowInMilliseconds",value = "3000")
            },
            fallbackMethod = "myFallback"

    )
    @GetMapping("/checkStateByRibbonTimeoutHystrix/{userId}")
    public Integer findResumeOpenStateByRibbonTimeoutHystrix(@PathVariable Integer userId){
        //1、使用ribbon不需要我们自己获取服务实例然后再选择一个，帮助我们去负载均衡
        String url = "http://cloud-service-resume/resume/openstate/" + userId;
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }

    /**
     * 定义熔断服务降级回退方法
     * 注意该方法的形参和返回值和原方法保持一致
     * @param userId
     * @return
     */
    public Integer myFallback(Integer userId){
        return -1;
    }
}
