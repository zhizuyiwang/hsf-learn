package com.hsf.boot.test.controller;

import com.hsf.boot.test.cache.DefaultCacheManage;
import com.hsf.boot.test.config.RedisConfig;
import com.hsf.boot.test.dao.TeacherRepository;
import com.hsf.boot.test.dao.UserRepository;
import com.hsf.boot.test.pojo.Person;
import com.hsf.boot.test.pojo.Student;
import com.hsf.boot.test.pojo.Teacher;
import com.hsf.boot.test.pojo.User;
import com.hsf.config.SimpleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    Person person;
    @Autowired
    SimpleBean simpleBean;
    @Autowired
    Teacher teacher;
    @Autowired
    Student student;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @GetMapping("/test")
    @ResponseBody
    public String test(){
        User query = new User();
        query.setId(2);
        Example<User> of = Example.of(query);
        Optional<User> one = userRepository.findOne(of);
        User user = one.get();
        return user.toString();
    }
    @GetMapping("/testRedis")
    @ResponseBody
    public String testRedis(){
        Teacher teacher = new Teacher();
        Student student = new Student();
        student.setName("hsf");
        teacher.setId("lll");
        teacher.setName("jing");
        teacher.setAge(13);
        teacher.setStudent(student);
        Teacher save = teacherRepository.save(teacher);
        return save.toString();
    }
    @GetMapping("/testRedis2")
    @ResponseBody
    public String testRedis2(){
        List<Teacher> teachers = teacherRepository.findByName("jing");
        return teachers.get(0).toString();
    }

    @GetMapping("/testRedis3")
    @ResponseBody
    public String testRedis3(){
        List<Teacher> teachers = teacherRepository.findByAge(13);
        return teachers.get(0).toString();
    }
    @GetMapping("/login")
    public String toLogin(Model model){
        model.addAttribute("currentYear", Calendar.getInstance().get(Calendar.YEAR));
        model.addAttribute("name","hsf");
        return "login";
    }

    @Autowired
    DefaultCacheManage defaultCacheManage;
    @GetMapping("/cacheTest")
    @ResponseBody
    public String getCache(){
        return defaultCacheManage.createCache("123");

    }
    @GetMapping("/cacheTest1")
    @ResponseBody
    public String delCache(){
        return defaultCacheManage.deleteCache("1234");

    }
    @GetMapping("/cacheTest2")
    @ResponseBody
    public String updateCache(){
        return defaultCacheManage.update("12345");

    }
    @GetMapping("/log")
    @ResponseBody
    public String log(){
        logger.debug("测试日志");
        return "打印log";
    }
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        long time = date.getTime();
        System.out.println(time);
        System.out.println(DateUtils.getDate(time));
    }
}
