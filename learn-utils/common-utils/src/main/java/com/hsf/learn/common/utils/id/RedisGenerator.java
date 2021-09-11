package com.hsf.learn.common.utils.id;

import redis.clients.jedis.Jedis;

public class RedisGenerator {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("123456");
        Long id = jedis.incr("id");//<id,0>
        System.out.println(id);
    }
}
