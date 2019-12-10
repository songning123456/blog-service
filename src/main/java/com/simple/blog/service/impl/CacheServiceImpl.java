package com.simple.blog.service.impl;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.dto.LabelDTO;
import com.simple.blog.entity.LabelConfig;
import com.simple.blog.entity.SystemConfig;
import com.simple.blog.repository.LabelConfigRepository;
import com.simple.blog.repository.LabelRelationRepository;
import com.simple.blog.repository.SystemConfigRepository;
import com.simple.blog.service.CacheService;
import com.simple.blog.service.RedisService;
import com.simple.blog.util.DataBaseUtil;
import com.simple.blog.util.HttpServletRequestUtil;
import com.simple.blog.util.JsonUtil;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author: songning
 * @date: 2019/11/29 21:11
 */
@Service
@Slf4j
public class CacheServiceImpl implements CacheService {

    @Autowired
    private SystemConfigRepository systemConfigRepository;
    @Autowired
    private LabelConfigRepository labelConfigRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private DataBaseUtil dataBaseUtil;
    @Autowired
    private LabelRelationRepository labelRelationRepository;
    @Autowired
    private HttpServletRequestUtil httpServletRequestUtil;

    @Override
    public void refreshLabelConfig() {
        List<LabelConfig> labelConfigList;
        labelConfigList = labelConfigRepository.findAll();
        Map<String, String> labelConfigMap = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.ALL_LABEL);
        if (!labelConfigMap.isEmpty()) {
            redisService.deleteValues(CommonConstant.REDIS_CACHE, CommonConstant.ALL_LABEL);
        }
        this.cacheLabel(labelConfigList);
        log.info("~~~刷新LabelConfig缓存~~~");
    }

    @Override
    public void refreshSystemConfig(String username){
        Map<String, String> systemConfigMap = redisService.getValues(CommonConstant.REDIS_CACHE + CommonConstant.SYSTEM_CONFIG + username);
        if (!systemConfigMap.isEmpty()) {
            redisService.deleteValues(CommonConstant.REDIS_CACHE, CommonConstant.SYSTEM_CONFIG, username);
        }
        List<SystemConfig> systemConfigList = systemConfigRepository.getSystemConfigByUsername(username);
        for (SystemConfig systemConfig: systemConfigList) {
            redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.SYSTEM_CONFIG + username + ":" + systemConfig.getConfigKey(), JsonUtil.convertObject2String(systemConfig));
        }
        log.info("~~~user:刷新SystemConfig缓存~~~");
    }

    @Override
    public void refreshPersonAttentionLabel(String username) {
        String person = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.PERSON_ATTENTION_LABEL + username);
        if (!StringUtils.isEmpty(person)) {
            redisService.deleteValue(CommonConstant.REDIS_CACHE + CommonConstant.PERSON_ATTENTION_LABEL + username);
        }
        List labelNames = labelRelationRepository.findLabelNameByUsernameAndSelectedNative(username, 1);
        redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.PERSON_ATTENTION_LABEL + username, JsonUtil.convertObject2String(labelNames));
        log.info("~~~user:刷新PersonAttentionLabel缓存~~~");
    }

    @Override
    public List getPersonAttentionLabelCache() throws Exception{
        String username = httpServletRequestUtil.getUsername();
        if (StringUtils.isEmpty(username)) {
            throw new Exception("token无效,请重新登陆");
        }
        String person = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.PERSON_ATTENTION_LABEL + username);
        List labelNames;
        if (StringUtils.isEmpty(person)) {
            labelNames = labelRelationRepository.findLabelNameByUsernameAndSelectedNative(username, 1);
            redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.PERSON_ATTENTION_LABEL + username, JsonUtil.convertObject2String(labelNames));
        } else {
            labelNames = JsonUtil.convertString2Object(person, List.class);
        }
        return labelNames;
    }

    @Override
    public Map<String, String> getLabelConfigCache() {
        Map<String, String> labelConfigMap = redisService.getValues(CommonConstant.REDIS_CACHE + CommonConstant.ALL_LABEL);
        if (labelConfigMap.isEmpty()) {
            List<LabelConfig> labelConfigList = labelConfigRepository.findAll();
            this.cacheLabel(labelConfigList);
            labelConfigMap = redisService.getValues(CommonConstant.REDIS_CACHE + CommonConstant.ALL_LABEL);
        }
        return labelConfigMap;
    }

    @Override
    public Map<String, String> getSystemConfigCache() throws Exception{
        String username = httpServletRequestUtil.getUsername();
        if (StringUtils.isEmpty(username)) {
            throw new Exception("token无效,请重新登陆");
        }
        Map<String, String> systemConfigMap = redisService.getValues(CommonConstant.REDIS_CACHE + CommonConstant.SYSTEM_CONFIG + username);
        if (systemConfigMap.isEmpty()) {
            List<SystemConfig> systemConfigList = systemConfigRepository.getSystemConfigByUsername(username);
            for (SystemConfig systemConfig: systemConfigList) {
                redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.SYSTEM_CONFIG + username + ":" + systemConfig.getConfigKey(), JsonUtil.convertObject2String(systemConfig));
            }
            systemConfigMap = redisService.getValues(CommonConstant.REDIS_CACHE + CommonConstant.SYSTEM_CONFIG + username);
        }
        return systemConfigMap;
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
