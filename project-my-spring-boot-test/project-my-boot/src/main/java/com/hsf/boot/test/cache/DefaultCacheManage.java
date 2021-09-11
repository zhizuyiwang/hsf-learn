package com.hsf.boot.test.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DefaultCacheManage {

    @Cacheable(cacheNames = "caches")
    public String createCache(String id){
        return "100";
    }
    @CachePut(cacheNames = "caches")
    public String update(String id){
        return "200";
    }
    @CacheEvict(cacheNames = "caches")
    public String deleteCache(String id){
        return "删除缓存";
    }
}
