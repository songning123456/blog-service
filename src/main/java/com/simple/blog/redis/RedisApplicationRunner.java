package com.simple.blog.redis;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.entity.LabelGroup;
import com.simple.blog.entity.LabelRelation;
import com.simple.blog.entity.SystemConfig;
import com.simple.blog.repository.LabelGroupRepository;
import com.simple.blog.repository.LabelRelationRepository;
import com.simple.blog.repository.SystemConfigRepository;
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
    @Autowired
    private LabelRelationRepository labelRelationRepository;
    @Autowired
    private SystemConfigRepository systemConfigRepository;

    @Override
    @Async
    public void run(ApplicationArguments arguments) {
        List<LabelGroup> labelGroupList;
        List<LabelRelation> labelRelationList;
        List<SystemConfig> systemConfigList;
        // 查询数据库加锁 只需要查询一次即可
        synchronized (this) {
            labelGroupList = labelGroupRepository.findAll();
            labelRelationList = labelRelationRepository.findAll();
            systemConfigList = systemConfigRepository.findAll();
        }
        // 获取redis数据
        Map<String, Object> groupMap = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_GROUP);
        Map<String, Object> relationMap = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_RELATION);
        Map<String, Object> systemConfigMap = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.SYSTEM_CONFIG);
        if (groupMap.isEmpty()) {
            labelGroupList.forEach(item -> redisService.setValue(item.getLabelGroupName(), item, CommonConstant.REDIS_CACHE + CommonConstant.LABEL_GROUP));
        } else {
            redisService.deleteValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_GROUP);
            labelGroupList.forEach(item -> redisService.setValue(item.getLabelGroupName(), item, CommonConstant.REDIS_CACHE + CommonConstant.LABEL_GROUP));
        }
        if (relationMap.isEmpty()) {
            labelRelationList.forEach(item -> redisService.setValue(item.getLabelGroupName() + "-" + item.getLabelName(), item, CommonConstant.REDIS_CACHE + CommonConstant.LABEL_RELATION));
        } else {
            redisService.deleteValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_RELATION);
            labelRelationList.forEach(item -> redisService.setValue(item.getLabelGroupName() + "-" + item.getLabelName(), item, CommonConstant.REDIS_CACHE + CommonConstant.LABEL_RELATION));
        }
        if (systemConfigMap.isEmpty()) {
            systemConfigList.forEach(item -> redisService.setValue(item.getConfigKey(), item, CommonConstant.REDIS_CACHE + CommonConstant.SYSTEM_CONFIG));
        } else {
            redisService.deleteValues(CommonConstant.REDIS_CACHE, CommonConstant.SYSTEM_CONFIG);
            systemConfigList.forEach(item -> redisService.setValue(item.getConfigKey(), item, CommonConstant.REDIS_CACHE + CommonConstant.SYSTEM_CONFIG));
        }
        log.info("^^^^^缓存redis成功^^^^^");
    }
}
