package com.hsf.learn.demo.redistest;

import com.hsf.learn.common.redis.config.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisControllerTestTest {
    @Resource(name = "redisUtil")
    RedisUtil redisUtil;

    @Test
    public void test1(){
    }

}