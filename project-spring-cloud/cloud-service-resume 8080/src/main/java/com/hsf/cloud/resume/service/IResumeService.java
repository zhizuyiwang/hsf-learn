package com.hsf.cloud.resume.service;

import com.hsf.cloud.common.pojo.Resume;

public interface IResumeService {
    Resume findDefaultResumeByUserId(Long userId);
}
