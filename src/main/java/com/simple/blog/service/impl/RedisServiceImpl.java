package com.simple.blog.service.impl;

import com.simple.blog.service.RedisService;
import com.simple.blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void deleteValues(String... name) {
        Set<String> keys = stringRedisTemplate.keys(StringUtil.getString(name) + "*");
        stringRedisTemplate.delete(keys);
    }

    @Override
    public Map<String, String> getValues(String... name) {
        Map<String, String> map = new HashMap<>(16);
        Set<String> sets = stringRedisTemplate.keys(StringUtil.getString(name) + "*");
        for (String set : sets) {
            String key = set;
            if (key.indexOf("~keys") > 0) {
                continue;
            }
            String value = this.getValue(key);
            map.put(key, value);
        }
        return map;
    }

    @Override
    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void setValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }
}
