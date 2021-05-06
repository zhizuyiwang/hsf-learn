package com.hsf.my.netty.batis.dao;

import com.hsf.my.netty.batis.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> findAll() throws Exception;

    User  findByNameAndId(User user) throws Exception;
}
