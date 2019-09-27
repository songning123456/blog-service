package com.simple.blog.constant;

/**
 * @Author songning
 * @create 2019/7/31 14:03
 */
public class CommonConstant {

    /**
     * redis缓存目录
     */
    public static final String REDIS_CACHE = "RedisCache:";
    public static final String LABEL_GROUP = "LabelGroup:";
    public static final String LABEL_RELATION = "LabelRelation:";
    public static final String SYSTEM_CONFIG = "SystemConfig:";

    /**
     * elasticsearch 索引名
     */
    public static final String INDEX_NAME = "simple_blog";
    public static final String TYPE_NAME = "es_blog";

    /**
     * 时间转换格式
     */
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String YEAR_DATETIME_PATTERN = "yyyy-MM-dd";

    /**
     * jwt 角色种类
     */
    public static final String LOGIN_ADMIN = "ADMIN";
    public static final String LOGIN_USER = "USER";

    /**
     * 数据源
     */
    public static final String DATABASE_ES = "ES";
    public static final String DATABASE_MYSQL = "MYSQL";
}
