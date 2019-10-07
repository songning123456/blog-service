package com.simple.blog.util;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.repository.SystemConfigRepository;
import com.simple.blog.service.BlogService;
import com.simple.blog.service.RedisService;
import com.simple.blog.service.impl.EsBlogServiceImpl;
import com.simple.blog.service.impl.MysqlBlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author songning
 * @date 2019/9/27
 * description
 */
@Component
public class DataBaseUtil {

    @Autowired
    private MysqlBlogServiceImpl mysqlBlogService;

    @Autowired
    private EsBlogServiceImpl esBlogService;

    @Autowired
    private SystemConfigRepository systemConfigRepository;

    @Autowired
    private RedisService redisService;

    public BlogService getDataBase() {
        String username = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.LOGIN_INFO + "username");
        String dataBase = systemConfigRepository.findConfigValueByUsernameAndConfigKeyNative(username, "dataBase");
        if (CommonConstant.DATABASE_MYSQL.equals(dataBase)) {
            return mysqlBlogService;
        } else {
            return esBlogService;
        }
    }
}
