package com.hsf.cloud.resume.service.impl;

import com.hsf.cloud.common.pojo.Resume;
import com.hsf.cloud.resume.dao.ResumeDao;
import com.hsf.cloud.resume.service.IResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl implements IResumeService {
    @Autowired
    private ResumeDao resumeDao;
    @Override
    public Resume findDefaultResumeByUserId(Long userId) {
        Resume resume = new Resume();
        resume.setId(userId);
        resume.setIsDefault(1);
        Example<Resume> example = Example.of(resume);
        return resumeDao.findOne(example).get();
    }
}
