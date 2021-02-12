package com.hsf.learn.gmall.manage.service.impl;

import com.hsf.learn.common.redis.config.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AttrServiceImplTest {

//    @Autowired
//    RedisUtil redisUtil;
    @Autowired
    CatalogServiceImpl catalogService;

    @Test
    public void test(){
        System.out.println(catalogService);
    }
}