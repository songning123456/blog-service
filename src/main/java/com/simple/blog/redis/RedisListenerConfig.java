package com.simple.blog.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author songning
 * @date 2019/11/4
 * description
 */
@Configuration
public class RedisListenerConfig {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 处理乱码
     *
     * @return
     */
    @Bean
    public RedisTemplate redisTemplateInit() {
        // key序列化
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        //val实例化
        stringRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return stringRedisTemplate;
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
}
