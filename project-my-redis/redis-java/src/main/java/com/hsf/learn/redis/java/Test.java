package com.hsf.learn.redis.java;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        sentinelRedis();

    }

    public static void sentinelRedis(){
        String masterName = "redis-master";
        String pwd = "123456";
        HashSet<String> sentinels = new HashSet<>();
        sentinels.add("192.168.95.100:26379");
        sentinels.add("192.168.95.110:26379");
        sentinels.add("192.168.95.120:26379");
        JedisSentinelPool pool = new JedisSentinelPool(masterName, sentinels, pwd);
        pool.getResource().set("score",100+"");
        System.out.println(pool.getResource().get("score"));

    }
    public static void  singleRedis(){
        Jedis jedis = new Jedis("192.168.95.100", 6379);
        jedis.auth("123456");
        jedis.set("jedis:name:1","hsf");
        System.out.println(jedis.get("jedis:name:1"));
        jedis.lpush("jedis:list:1","1","2","3","4");
        System.out.println(jedis.lrange("jedis:list:1",0,-1));
        List<String> sort = jedis.sort("jedis:list:1");
        System.out.println(sort);
        System.out.println(jedis.randomKey());
    }

}
