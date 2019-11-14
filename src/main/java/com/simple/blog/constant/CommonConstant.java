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
    public static final String ALL_LABEL = "AllLabel:";
    public static final String PERSON_ATTENTION_LABEL = "PersonAttentionLabel:";
    public static final String SYSTEM_CONFIG = "SystemConfig:";
    public static final String LOGIN_INFO = "login:";
    public static final String LABEL_CONFIG = "LabelConfig:";

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
    public static final String FLYWAY_SQL_DATETIME_PATTERN = "yyyy.MM.dd_HHmm";

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

    /**
     * 保存的历史记录类型
     */
    public static final String READ_ARTICLE = "阅读文章";
    public static final String SIMPLE_CLOCK = "休眠时钟";
    public static final String SIMPLE_MAP = "定位地图";
}
