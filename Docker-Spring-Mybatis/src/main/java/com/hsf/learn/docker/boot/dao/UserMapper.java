package com.hsf.learn.docker.boot.dao;


import com.hsf.learn.docker.boot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    User find(String username);
    List<User> list();
    int insert(User user);
    int delete(int id);
    int update(User user);
}
