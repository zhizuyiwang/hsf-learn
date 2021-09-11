package com.hsf.boot.test.pojo;

import org.springframework.data.redis.core.index.Indexed;

public class Student {
    @Indexed
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
