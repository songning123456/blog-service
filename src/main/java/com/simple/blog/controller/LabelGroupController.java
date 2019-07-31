package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelGroupDTO;
import com.simple.blog.service.LabelGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author songning
 * @create 2019/7/31 18:01
 */
@RestController
@Slf4j
@RequestMapping(value = "/label")
public class LabelGroupController {
    @Autowired
    private LabelGroupService labelGroupService;

    @PostMapping("/queryLabelCache")
    @ControllerAspectAnnotation(description = "查询标签缓存")
    public CommonDTO<LabelGroupDTO> queryLabelCache() {
        CommonDTO<LabelGroupDTO> commonDTO = labelGroupService.getLabelCache();
        return commonDTO;
    }
}
