package com.hsf.cloud.autodeliver.controller;

import com.hsf.cloud.autodeliver.service.ResumeFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autodeliver")
public class AutoDeliverController {
    @Autowired
    ResumeFeignClient resumeFeignClient;

    @GetMapping("/checkStateByFeign/{userId}")
    public Integer findResumeOpenStateByRibbonTimeoutHystrix(@PathVariable Integer userId){
       return resumeFeignClient.findResumeOpenStateByFeign(userId);
    }

}
