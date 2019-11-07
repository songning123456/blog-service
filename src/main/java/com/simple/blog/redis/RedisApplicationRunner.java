package com.simple.blog.redis;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.dto.LabelDTO;
import com.simple.blog.entity.LabelConfig;
import com.simple.blog.entity.SystemConfig;
import com.simple.blog.repository.LabelConfigRepository;
import com.simple.blog.repository.LabelRelationRepository;
import com.simple.blog.repository.SystemConfigRepository;
import com.simple.blog.service.RedisService;
import com.simple.blog.util.DataBaseUtil;
import com.simple.blog.util.JsonUtil;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
    private LabelConfigRepository labelConfigRepository;
    @Autowired
    private SystemConfigRepository systemConfigRepository;
    @Autowired
    private LabelRelationRepository labelRelationRepository;
    @Autowired
    private DataBaseUtil dataBaseUtil;

    @Override
    @Async
    public void run(ApplicationArguments arguments) {
        List<LabelConfig> labelConfigList;
        List<SystemConfig> systemConfigList;
        // 查询数据库加锁 只需要查询一次即可
        synchronized (this) {
            labelConfigList = labelConfigRepository.findAll();
            systemConfigList = systemConfigRepository.findAll();
        }
        // 获取redis数据
        Map<String, String> labelConfigMap = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.ALL_LABEL);
        Map<String, String> systemConfigMap = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.SYSTEM_CONFIG);
        if (labelConfigMap.isEmpty()) {
            this.cacheLabel(labelConfigList);
        } else {
            redisService.deleteValues(CommonConstant.REDIS_CACHE, CommonConstant.ALL_LABEL);
            this.cacheLabel(labelConfigList);
        }
        if (systemConfigMap.isEmpty()) {
            systemConfigList.forEach(item -> redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.SYSTEM_CONFIG + item.getUsername() + ":" + item.getConfigKey(), JsonUtil.convertObject2String(item)));
        } else {
            redisService.deleteValues(CommonConstant.REDIS_CACHE, CommonConstant.SYSTEM_CONFIG);
            systemConfigList.forEach(item -> redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.SYSTEM_CONFIG + item.getUsername() + ":" + item.getConfigKey(), JsonUtil.convertObject2String(item)));
        }
        log.info("^^^^^缓存redis成功^^^^^");
    }

    /**
     * 缓存标签labelConfig到redis
     *
     * @param labelConfigList
     */
    private void cacheLabel(List<LabelConfig> labelConfigList) {
        CommonVO<LabelVO> commonVO = new CommonVO<>();
        LabelVO labelVO = new LabelVO();
        Map<String, Object> countMap;
        for (LabelConfig labelConfig : labelConfigList) {
            // 统计关注数
            countMap = labelRelationRepository.countAttentionNative(labelConfig.getLabelName());
            long numOfAttention = 0L;
            if (!countMap.isEmpty() && countMap.get("total") != null) {
                numOfAttention = ((BigDecimal) countMap.get("total")).longValue();
            }
            // 统计文章总数
            labelVO.setLabelName(labelConfig.getLabelName());
            commonVO.setCondition(labelVO);
            Long numOfArticle = dataBaseUtil.getDataBase().statisticLabel(commonVO);
            // 缓存到redis
            LabelDTO labelDTO = LabelDTO.builder().id(labelConfig.getId()).labelName(labelConfig.getLabelName()).labelPhoto(labelConfig.getLabelPhoto()).numOfArticle(numOfArticle).numOfAttention(numOfAttention).build();
            redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.ALL_LABEL + labelConfig.getLabelName(), JsonUtil.convertObject2String(labelDTO));
        }
    }
}
