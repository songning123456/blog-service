package com.simple.blog.service.impl;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.SystemConfigDTO;
import com.simple.blog.entity.SystemConfig;
import com.simple.blog.repository.SystemConfigRepository;
import com.simple.blog.service.RedisService;
import com.simple.blog.service.SystemConfigService;
import com.simple.blog.util.ClassConvertUtil;
import com.simple.blog.util.HttpServletRequestUtil;
import com.simple.blog.util.JsonUtil;
import com.simple.blog.util.MapConvertEntityUtil;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.SystemConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.MapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: songning
 * @date: 2019/10/7 12:00
 */
@Service
public class SystemConfigServiceImpl implements SystemConfigService {

    @Autowired
    private SystemConfigRepository systemConfigRepository;

    @Autowired
    private RedisService redisService;
    @Autowired
    private HttpServletRequestUtil httpServletRequestUtil;

    @Override
    public CommonDTO<SystemConfigDTO> getSystemConfig(CommonVO<SystemConfigVO> commonVO) {
        CommonDTO<SystemConfigDTO> commonDTO = new CommonDTO<>();
        Integer recordStartNo = commonVO.getRecordStartNo();
        Integer pageRecordNum = commonVO.getPageRecordNum();
        String configKey = commonVO.getCondition().getConfigKey();
        String configValue = commonVO.getCondition().getConfigValue();
        String valueDescription = commonVO.getCondition().getValueDescription();
        String username = httpServletRequestUtil.getUsername();
        Sort sort = new Sort(Sort.Direction.ASC, "config_key");
        Pageable pageable = PageRequest.of(recordStartNo, pageRecordNum, sort);
        Page<SystemConfig> systemConfigPage = systemConfigRepository.findByUsernameAndConfigKeyAndConfigValueAndValueDescriptionNative(username, configKey, configValue, valueDescription, pageable);
        List<SystemConfig> systemConfigList = systemConfigPage.getContent();
        List<SystemConfigDTO> target = new ArrayList<>();
        ClassConvertUtil.populateList(systemConfigList, target, SystemConfigDTO.class);
        commonDTO.setData(target);
        commonDTO.setTotal((long) systemConfigList.size());
        return commonDTO;
    }

    @Override
    public <T> CommonDTO<T> updateSystemConfig(CommonVO<SystemConfigVO> commonVO) {
        String configKey = commonVO.getCondition().getConfigKey();
        String configValue = commonVO.getCondition().getConfigValue();
        String valueDescription = commonVO.getCondition().getValueDescription();
        String username = httpServletRequestUtil.getUsername();
        SystemConfig systemConfig = SystemConfig.builder().configKey(configKey).configValue(configValue).username(username).valueDescription(valueDescription).build();
        systemConfigRepository.updateSystemConfig(username, configKey, configValue, valueDescription);
        redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.SYSTEM_CONFIG + username + ":" + configKey, JsonUtil.convertObject2String(systemConfig));
        return new CommonDTO<>();
    }

    @Override
    public <T> CommonDTO<T> saveSystemConfig(CommonVO<SystemConfigVO> commonVO) {
        String username = commonVO.getCondition().getUsername();
        List<Map<String, Object>> mapList = systemConfigRepository.findDistinctNative();
        for (Map<String, Object> map : mapList) {
            try {
                SystemConfig systemConfig = (SystemConfig) MapConvertEntityUtil.mapToEntity(SystemConfig.class, map);
                systemConfig.setUsername(username);
                systemConfigRepository.save(systemConfig);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new CommonDTO<>();
    }
}
