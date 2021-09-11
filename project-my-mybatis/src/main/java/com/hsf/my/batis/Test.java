package com.hsf.my.batis;

import com.hsf.my.batis.dao.UserMapper;
import com.hsf.my.batis.io.Resources;
import com.hsf.my.batis.sqlSession.SqlSessionFactory;
import com.hsf.my.batis.sqlSession.SqlSessionFactoryBuild;
import com.hsf.my.batis.pojo.User;
import com.hsf.my.batis.sqlSession.SqlSession;

import java.io.InputStream;

public class Test {

    public static void main(String[] args) throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuild().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //调用
        User user = new User();
        user.setId(2L);
        user.setUsername("hsf");

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User result = userMapper.findByNameAndId(user);
//        List userResult = sqlSession.selectList("com.hsf.my.batis.selectList", user);
        System.out.println(result);

    }
}
