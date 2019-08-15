package com.simple.blog.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Author songning
 * @create 2019/8/15 17:26
 */
@Slf4j
@Component
public class ElasticsearchConfig implements InitializingBean {
    static {
        // 设置环境变量，解决Es的netty与Netty服务本身不兼容问题
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("*****************es_config*************************");
        log.info("es.set.netty.runtime.available.processors:{}", System.getProperty("es.set.netty.runtime.available.processors"));
        log.info("***************************************************");
    }
}
