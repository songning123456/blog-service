package com.simple.blog.service;

import java.util.Map;

/**
 * @Author songning
 * @create 2019/7/31 13:30
 */
public interface RedisService {

    /**
     * 删除指定文件夹下的所有缓存数据
     *
     * @param folder
     */
    void deleteValues(String... folder);


    /**
     * 根据指定文件夹获取所有缓存数据
     *
     * @param folder
     * @return
     */
    Map<String, Object> getValues(String... folder);

    /**
     * 设置指定问价夹下的值
     *
     * @param folder
     */
    void setValue(String key, Object value, String... folder);

}
