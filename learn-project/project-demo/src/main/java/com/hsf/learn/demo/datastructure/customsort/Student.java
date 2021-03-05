package com.hsf.learn.demo.datastructure.customsort;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                              //get，set
@NoArgsConstructor                 //无参构造
@AllArgsConstructor
public class Student implements Comparable<Student>{
    private String name;
    private int age;


    /**
     * 调用者的参数和传入的参数作比较，调用者的参数在前面是升序，否则是降序
     * @param student
     * @return
     */
    @Override
    public int compareTo(Student student) {
        return age - student.age;
    }

}
