package com.hsf.cloud.resume.controller;

import com.hsf.cloud.resume.service.IResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private IResumeService resumeService;
    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeByUserId(@PathVariable Long userId){
        return resumeService.findDefaultResumeByUserId(userId).getIsOpenResume();
    }
}
