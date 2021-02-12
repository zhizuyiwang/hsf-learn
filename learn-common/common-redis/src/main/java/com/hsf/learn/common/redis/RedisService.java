package com.hsf.learn.common.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author inspire
 * @date 2019-07-29
 * @apiNote 缓存接口
 */
@Service
public class RedisService {


    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(RedisService.class);


    /**
     * 获取数据
     * @param key
     * @return
     */
    public String get(String key) {

        String value;
        try {
            value = redisTemplate.opsForValue().get(key);
        } catch (Exception ex) {
            log.error("从缓存中获取数据失败;原因：" + ex.getMessage());
            throw new SmsException("从缓存中获取数据失败");
        }

        return value;
    }

    /**
     * 添加
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
        } catch (Exception ex) {
            log.error("向缓存中添加数据失败;原因：" + ex.getMessage());
            throw new SmsException("向缓存中添加数据失败");
        }
    }

    /**
     * 添加数据并设置超时时间
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */
    public void setWithExpire(String key, String value, long timeout, TimeUnit unit) {
        try {
            redisTemplate.opsForValue().set(key, value, timeout, unit);
        } catch (Exception ex) {
            log.error("向缓存中添加数据并设置超时时间失败;原因：" + ex.getMessage());
            throw new SmsException("向缓存中添加数据并设置超时时间失败");
        }
    }

    /**
     * 判断是否超时
     * @param key
     * @return
     */
    public boolean isExpire(String key) {
        Long expired = getExpire(RedisKeyConstants.USER_LOGIN_MESSAGE_KEY + key);
        if (expired > 0L) {
            return false;
        }
        return true;
    }

    /**
     * 设置超时时间
     * @param key
     * @param timeout
     * @param unit
     */
    public void expire(String key, long timeout, TimeUnit unit) {
        try {
            redisTemplate.expire(key, timeout, unit);
        } catch (Exception ex) {
            log.error("向缓存中设置超时时间失败;原因：" + ex.getMessage());
            throw new SmsException("向缓存中设置超时时间失败");
        }
    }

    /**
     * 获取超时时间
     * @param key
     * @return
     */
    public long getExpire(String key) {
        long expire;

        try {
            expire = redisTemplate.getExpire(key);
        } catch (Exception ex) {
            log.error("从缓存中获取超时时间失败;原因：" + ex.getMessage());
            throw new SmsException("从缓存中获取超时时间失败");
        }

        return expire;
    }

    /**
     * 自增
     */
    public long increment(String key, long value) {

        long result;
        try {
            result = redisTemplate.opsForValue().increment(key, value);
        } catch (Exception ex) {
            log.error("从缓存中修改数据失败;原因：" + ex.getMessage());
            throw new SmsException("从缓存中修改数据失败");
        }
        return result;
    }

    /**
     * 删除
     * @param key
     */
    public void delete(String key) {

        try {
            redisTemplate.delete(key);
        } catch (Exception ex) {
            log.error("从缓存中删除数据失败;原因：" + ex.getMessage());
            throw new SmsException("从缓存中删除数据失败");
        }
    }


    /**
     * 向列表中添加元素
     *
     * @param key
     * @param value
     */
    public void setList(String key, Integer value) {
        try {
            redisTemplate.opsForList().rightPush(key, value.toString());
        } catch (Exception ex) {
            log.error("向缓存中添加数据失败;原因：" + ex.getMessage());
            throw new SmsException("向缓存中添加数据失败");
        }

    }

    /**
     * 向列表中添加元素
     *
     * @param key
     * @param list
     */
    public void setList(String key, List<String> list) {
        try {
            redisTemplate.opsForList().rightPushAll(key, list);
        } catch (Exception ex) {
            log.error("数据同步reportId时缓存失败;原因：" + ex.getMessage());
            throw new SmsException("数据同步时缓存失败");
        }
    }

    /**
     * 遍历列表
     * @param key
     * @return
     */
    public List<String> getList(String key) {
        List<String> list = redisTemplate.opsForList().range(key, 0, -1);
        return list;
    }

    /**
     * 查询列表最后一个元素（最大ID）
     *
     * @param key
     * @return
     */
    public String getListLastOne(String key) {
        Long length = redisTemplate.opsForList().size(key);
        String value = redisTemplate.opsForList().index(key, length - 1);
        return value;
    }

    /**
     * 获取list长度
     * @param key
     * @return
     */
    public long getListLength(String key) {
        long length = redisTemplate.opsForList().size(key);
        return length;
    }

}
