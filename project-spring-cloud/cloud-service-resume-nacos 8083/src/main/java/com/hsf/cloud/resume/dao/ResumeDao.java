package com.hsf.cloud.resume.dao;

import com.hsf.cloud.common.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeDao extends JpaRepository<Resume,Long>{


}
