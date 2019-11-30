package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.service.CacheService;
import com.simple.blog.util.HttpServletRequestUtil;
import com.simple.blog.vo.CacheVO;
import com.simple.blog.vo.CommonVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author: songning
 * @date: 2019/11/29 21:31
 */
@RestController
@Slf4j
@RequestMapping(value = "/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;
    @Autowired
    private HttpServletRequestUtil httpServletRequestUtil;

    @PostMapping("/refresh")
    @ControllerAspectAnnotation(description = "刷新缓存")
    public <T> CommonDTO<T> refreshCaches(@RequestBody CommonVO<CacheVO> commonVO) throws Exception {
        boolean systemConfig = commonVO.getCondition().getSystemConfig();
        boolean labelConfig = commonVO.getCondition().getLabelConfig();
        boolean personalAttentionLabel = commonVO.getCondition().getPersonalAttentionLabel();
        String username = httpServletRequestUtil.getUsername();
        if (StringUtils.isEmpty(username)) {
            throw new Exception("token无效,请重新登陆");
        }
        if (labelConfig) {
            cacheService.refreshLabelConfig();
        }
        if (systemConfig) {
            cacheService.refreshSystemConfig(username);
        }
        if (personalAttentionLabel) {
            cacheService.refreshPersonAttentionLabel(username);
        }
        return new CommonDTO<>();
    }
}
