package com.hsf.boot.test.dao;

import com.hsf.boot.test.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
