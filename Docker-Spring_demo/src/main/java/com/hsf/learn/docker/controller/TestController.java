package com.hsf.learn.docker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/docker")
    public String test(){
        return "hello, docker";
    }
}
