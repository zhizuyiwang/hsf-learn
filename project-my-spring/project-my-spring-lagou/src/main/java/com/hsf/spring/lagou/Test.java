package com.hsf.spring.lagou;

public class Test {
    public static void main(String[] args) {
        Teacher teacher = (Teacher) BeanFactory.getBean("teacher");
        teacher.teach();

    }
}
