package com.simple.blog.service.impl;

import com.simple.blog.service.RedisService;
import com.simple.blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Author songning
 * @create 2019/7/31 13:30
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void deleteValues(String... name) {
        String folder = StringUtil.getString(name);
        Set<String> keys = redisTemplate.keys(folder + "*");
        redisTemplate.delete(keys);
    }

    @Override
    public Map<String, Object> getValues(String... name) {
        Map<String, Object> map = new HashMap<>(10);
        String folder = StringUtil.getString(name);
        Set<String> keys = redisTemplate.keys(folder + "*");
        for (String key : keys) {
            if (key.indexOf("~keys") > 0) {
                continue;
            }
            Object value = redisTemplate.opsForValue().get(key);
            map.put(key, value);
        }
        return map;
    }

    @Override
    public void setValue(String key, Object value, String... name) {
        String folder = StringUtil.getString(name);
        redisTemplate.opsForValue().set(folder + key, value);
    }
}
