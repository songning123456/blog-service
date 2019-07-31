package com.simple.blog.redis;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.entity.LabelGroup;
import com.simple.blog.repository.LabelGroupRepository;
import com.simple.blog.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Author songning
 * @create 2019/7/31 13:24
 */
@Slf4j
@Component
public class RedisApplicationRunner implements ApplicationRunner {

    @Autowired
    private RedisService redisService;

    @Autowired
    private LabelGroupRepository labelGroupRepository;

    @Override
    @Async
    public void run(ApplicationArguments arguments) {
        List<LabelGroup> list = labelGroupRepository.findAll();
        Map<String, Object> result = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_GROUP);
        if (result.isEmpty()) {
            list.forEach(item -> redisService.setValue(item.getLabelGroupName(), item, CommonConstant.REDIS_CACHE + CommonConstant.LABEL_GROUP));
        } else {
            redisService.deleteValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_GROUP);
            list.forEach(item -> redisService.setValue(item.getLabelGroupName(), item, CommonConstant.REDIS_CACHE + CommonConstant.LABEL_GROUP));
        }
    }
}
