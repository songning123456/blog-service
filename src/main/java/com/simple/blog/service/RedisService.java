package com.simple.blog.service;

import java.util.Map;

/**
 * @Author songning
 * @create 2019/7/31 13:30
 */
public interface RedisService {

    void deleteValues(String... name);

    Map<String, String> getValues(String... name);

    String getValue(String key);

    void setValue(String key, String value);

    void setExpireValue(String key,String value, long timeout);
}
