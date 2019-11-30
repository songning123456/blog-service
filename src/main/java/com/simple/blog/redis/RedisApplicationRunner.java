package com.simple.blog.redis;

import com.simple.blog.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Author songning
 * @create 2019/7/31 13:24
 */
@Slf4j
@Component
public class RedisApplicationRunner implements ApplicationRunner {

    @Autowired
    private CacheService cacheService;

    @Override
    @Async
    public void run(ApplicationArguments arguments) {
        cacheService.refreshSystemConfig();
        cacheService.refreshLabelConfig();
        log.info("^^^^^缓存redis成功^^^^^");
    }
}
