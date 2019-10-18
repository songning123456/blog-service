package com.simple.blog.service.impl;

import com.simple.blog.service.RedisService;
import com.simple.blog.util.StringUtil;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Map<String, String> map = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String c1 = o1.split(":")[o1.split(":").length - 1];
                String c2 = o2.split(":")[o2.split(":").length - 1];
                return c1.compareTo(c2);
            }
        });
        Set<String> sets = stringRedisTemplate.keys(StringUtil.getString(name) + "*");
        for (String key : Objects.requireNonNull(sets)) {
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
