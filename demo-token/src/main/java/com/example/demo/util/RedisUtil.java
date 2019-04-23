package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author: ayh
 * @create: 2019-04-10 14:19
 **/
@Component
@Slf4j
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 写入缓存,过期时间
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            // 分钟
            redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
            result = true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 设置分布式锁
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean lock(String key, Long expire) {
        // 获取redis连接
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        // 自己设置的连接，需重新设置字符集
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        // 若有值,则成功
        if (connection.setNX(key.getBytes(), new byte[]{1})) {
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
            connection.close();
            return true;
        } else {
            connection.close();
            return false;
        }
    }

    /**
     * 解锁
     */
    public void unLock(String key) {
        // 自己设置的连接，需重新设置字符集
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.delete(key);
    }


}
