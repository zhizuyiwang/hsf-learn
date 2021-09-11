package com.hsf.my.batis.dao;

import com.hsf.my.batis.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> findAll() throws Exception;

    User  findByNameAndId(User user) throws Exception;
}
