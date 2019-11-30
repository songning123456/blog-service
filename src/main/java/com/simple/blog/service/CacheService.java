package com.simple.blog.service;

/**
 * @author: songning
 * @date: 2019/11/29 21:10
 */
public interface CacheService {

    /**
     * 刷新 SystemConfig 缓存
     */
    void refreshSystemConfig();

    /**
     * 刷新LabelConfig 缓存
     */
    void refreshLabelConfig();
}
