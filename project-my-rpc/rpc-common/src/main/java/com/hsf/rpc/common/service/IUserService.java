package com.hsf.rpc.common.service;

public interface IUserService {

    /**
     * 参数和返回值一定要支持序列化，String本来就是支持序列化的
     * @param word
     * @return
     */
    String sayHello(String word);
}
