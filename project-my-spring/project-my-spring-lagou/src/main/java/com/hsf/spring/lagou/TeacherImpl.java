package com.hsf.spring.lagou;

public class TeacherImpl implements Teacher{
    private Student student;
    public void setStudent(Student student){
        this.student = student;
    }
    @Override
    public void teach() {
        System.out.println("教学");
        student.study();
    }
}
