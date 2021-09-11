package com.hsf.learn.redis.boot.controller;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class RedisController {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/put")
    public String putData(String key, String value){
        redisTemplate.opsForValue().set(key, value,10, TimeUnit.SECONDS);
        return "success";
    }
    @GetMapping("/get")
    public void getData(String key){
        System.out.println(redisTemplate.opsForValue().get(key));
    }
}
