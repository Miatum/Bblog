package com.miatum.blog.util;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description redis工具类
 * @ClassName RedisUtil
 * @Author Miatum
 * @date 2021.04.12 15:43
 */
@Component
public class RedisUtil<T> {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * @Description redis String
     * @Param
     * @return
     * @Author Miatum
     * @Date 2021.05.12 17:19
     **/
    public boolean set(final String key, T value) {
        try {
            redisTemplate.opsForValue().set(key, JSON.toJSONString(value));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public T get(String key, Class<T> clazz) {
        String value = (String) redisTemplate.boundValueOps(key).get();
        if (value == null) {
            return null;
        }
        return JSON.parseObject(value, clazz);
    }
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }
    public Long del(Collection<String> keys) {
        return redisTemplate.delete(keys);
    }
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }
    /**
     * @Description redis set集合
     * @Param
     * @return
     * @Author Miatum
     * @Date 2021.05.12 17:19
     **/
    // 集合添加元素
    public boolean sadd (String key, T... values) {
        for (T value : values) {
            redisTemplate.opsForSet().add(key, JSON.toJSONString(value));
        }
        return true;
    }
    // 获取集合所有元素，返回Set类型
    public Set smembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }
    // 验证是否为集合元素
    public boolean isMember(String key, T object) {
        return redisTemplate.opsForSet().isMember(key, JSON.toJSONString(object));
    }
    // 移除集合元素,返回移除个数
    public Long srem(String key, T... values) {
        for (T value : values) {
            redisTemplate.opsForSet().remove(key, JSON.toJSONString(value));
        }
        return (long)values.length;
    }
}
