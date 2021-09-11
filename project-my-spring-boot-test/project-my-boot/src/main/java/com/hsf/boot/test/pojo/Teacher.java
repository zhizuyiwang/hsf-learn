package com.hsf.boot.test.pojo;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;

@RedisHash("teacher")//指定操作的实体类对象在Redis数据库中的存储空间
public class Teacher {
    @Id //用来标识实体类主键  字符串形式的hashkey标识唯一的实体类对象id
    private String id;
    @Indexed //用来标识对应属性在redis中生成二级索引
    private String name;
    @Indexed
    private int age;
    private Student student;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", student=" + student +
                '}';
    }
}
