package com.hsf.learn.redis.spring;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("classpath:redis.xml")
public class TestRedis extends AbstractJUnit4SpringContextTests {
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Test
    public void testConn(){
        redisTemplate.opsForValue().set("spring:redis:name","SpringRedisTest");
        System.out.println(redisTemplate.opsForValue().get("spring:redis:name"));
    }
}
