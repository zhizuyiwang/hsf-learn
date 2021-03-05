package com.hsf.learn.demo.datastructure.customsort;


import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        Student []students = new Student[]{
                new Student("张三",90),
                new Student("王老五",100),
                new Student("李四",87),
                new Student("小明",99)
        };
        Arrays.sort(students);
        for (Student student : students){
            System.out.println(student);
        }

        Book []books = new Book[]{
                new Book("Python",90),
                new Book("Java",100),
                new Book("Go",87),
                new Book("JS",99)

        };
        CustomArraysSort.Sort(books);

        for (Book book : books){
            System.out.println(book);
        }


    }
}
