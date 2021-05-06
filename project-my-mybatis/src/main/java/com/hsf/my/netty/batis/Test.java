package com.hsf.my.netty.batis;

import com.hsf.my.netty.batis.dao.UserMapper;
import com.hsf.my.netty.batis.io.Resources;
import com.hsf.my.netty.batis.pojo.User;
import com.hsf.my.netty.batis.sqlSession.SqlSession;
import com.hsf.my.netty.batis.sqlSession.SqlSessionFactory;
import com.hsf.my.netty.batis.sqlSession.SqlSessionFactoryBuild;

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
