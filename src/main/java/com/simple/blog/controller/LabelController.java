package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelRelationDTO;
import com.simple.blog.service.LabelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author songning
 * @create 2019/7/31 18:01
 */
@RestController
@Slf4j
@RequestMapping(value = "/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping("/querySelected")
    @ControllerAspectAnnotation(description = "获取用户关注的标签")
    public CommonDTO<LabelRelationDTO> getSelectedLabels() {
        CommonDTO<LabelRelationDTO> commonDTO = labelService.getSelectedLabel();
        return commonDTO;
    }
}
