package com.hsf.learn.demo.redistest;

import com.hsf.learn.common.redis.config.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RedisControllerTest {

    @Resource(name = "redisUtil")
    RedisUtil redisUtil;

    @GetMapping("/test")
    public String test(){
        return redisUtil.get("name");
    }

}
