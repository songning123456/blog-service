package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelGroupDTO;
import com.simple.blog.entity.LabelGroup;
import com.simple.blog.service.LabelGroupService;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelGroupVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author songning
 * @create 2019/7/31 18:01
 */
@RestController
@Slf4j
@RequestMapping(value = "/label")
public class LabelController {
    @Autowired
    private LabelGroupService labelGroupService;

    @PostMapping("/queryLabelCache")
    @ControllerAspectAnnotation(description = "查询标签缓存")
    public CommonDTO<LabelGroupDTO> queryLabelCache() {
        CommonDTO<LabelGroupDTO> commonDTO = labelGroupService.getLabelCache();
        return commonDTO;
    }

    @PostMapping("/insertLabelGroup")
    @ControllerAspectAnnotation(description = "插入标签分组名")
    public CommonDTO<LabelGroupDTO> insertLabelGroup(@RequestBody CommonVO<LabelGroupVO> commonVO) {
        CommonDTO<LabelGroupDTO> commonDTO = labelGroupService.saveLabelGroup(commonVO);
        return commonDTO;
    }
}
