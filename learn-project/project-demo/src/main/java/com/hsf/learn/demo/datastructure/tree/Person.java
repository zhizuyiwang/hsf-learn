package com.hsf.learn.demo.datastructure.tree;

public class Person implements Comparable<Person> {
    private Integer age;
    private Integer height;
    public Person(Integer age, Integer height){
        this.age = age;
        this.height = height;
    }

    @Override
    public int compareTo(Person element) {
        return this.age - element.age;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return
                "age=" + age 

                ;
    }
}
