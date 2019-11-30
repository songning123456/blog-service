package com.simple.blog.service;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取 personAttentionLabelCache缓存
     * @return
     * @throws Exception
     */
    List getPersonAttentionLabelCache() throws Exception;

    /**
     * 获取labelConfig缓存
     * @return
     */
    Map<String, String> getLabelConfigCache();
}
