package com.hsf.boot.test.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

// 测试启动器，并加载Spring Boot测试注解
@RunWith(SpringRunner.class)
// 标记为Spring Boot单元测试类，并加载项目的ApplicationContext上下文环境
@SpringBootTest
class TestControllerTest {
    @Autowired
    private TestController testController;
    @Test
    void test(){
        System.out.println(testController.test());
    }
}