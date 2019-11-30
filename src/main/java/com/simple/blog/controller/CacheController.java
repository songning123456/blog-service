package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.service.CacheService;
import com.simple.blog.vo.CacheVO;
import com.simple.blog.vo.CommonVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/refresh")
    @ControllerAspectAnnotation(description = "刷新缓存")
    public <T> CommonDTO<T> refreshCaches(@RequestBody CommonVO<CacheVO> commonVO) {
        boolean systemConfig = commonVO.getCondition().getSystemConfig();
        boolean labelConfig = commonVO.getCondition().getLabelConfig();
        if (systemConfig) {
            cacheService.refreshSystemConfig();
        }
        if (labelConfig) {
            cacheService.refreshLabelConfig();
        }
        return new CommonDTO<>();
    }
}
