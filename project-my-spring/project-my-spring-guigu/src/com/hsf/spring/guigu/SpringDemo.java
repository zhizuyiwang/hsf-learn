package com.hsf.spring.guigu;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class SpringDemo {
    @Autowired
    private StudentImpl student;

    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserTest userTest = (UserTest) context.getBean("userTest");
        userTest.eat();
        StudentImpl student = new StudentImpl();
        Student student1 = (Student) Proxy.newProxyInstance(SpringDemo.class.getClassLoader(),
                new Class[]{Student.class}, new StudentProxy(student));
        student1.eat();
    }
}
class StudentProxy implements InvocationHandler{
    private Object object;
    public StudentProxy(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName()+"方法执行前===参数==="+args);
        Object result = method.invoke(object, args);
        System.out.println(method.getName()+"方法执行后");
        return result;
    }
}
