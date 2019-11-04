package com.simple.blog.service.impl;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.constant.HttpStatus;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.SystemConfigDTO;
import com.simple.blog.entity.SystemConfig;
import com.simple.blog.repository.SystemConfigRepository;
import com.simple.blog.service.RedisService;
import com.simple.blog.service.SystemConfigService;
import com.simple.blog.util.ClassConvertUtil;
import com.simple.blog.util.HttpServletRequestUtil;
import com.simple.blog.util.JsonUtil;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.SystemConfigVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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
        if (StringUtils.isEmpty(username)) {
            commonDTO.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
            commonDTO.setMessage("token无效,请重新登陆");
            return commonDTO;
        }
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
        CommonDTO<T> commonDTO = new CommonDTO<>();
        String configKey = commonVO.getCondition().getConfigKey();
        String configValue = commonVO.getCondition().getConfigValue();
        String valueDescription = commonVO.getCondition().getValueDescription();
        String username = httpServletRequestUtil.getUsername();
        if (StringUtils.isEmpty(username)) {
            commonDTO.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
            commonDTO.setMessage("token无效,请重新登陆");
            return commonDTO;
        }
        SystemConfig systemConfig = SystemConfig.builder().configKey(configKey).configValue(configValue).username(username).valueDescription(valueDescription).build();
        systemConfigRepository.updateSystemConfig(username, configKey, configValue, valueDescription);
        redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.SYSTEM_CONFIG + username + ":" + configKey, JsonUtil.convertObject2String(systemConfig));
        return new CommonDTO<>();
    }
}
