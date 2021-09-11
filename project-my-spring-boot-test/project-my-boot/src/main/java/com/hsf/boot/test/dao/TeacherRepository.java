package com.hsf.boot.test.dao;

import com.hsf.boot.test.pojo.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends CrudRepository<Teacher,String> {
    List<Teacher> findByName(String name);
    List<Teacher> findByAge(int age);
}
