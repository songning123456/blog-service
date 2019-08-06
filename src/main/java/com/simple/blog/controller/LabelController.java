package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelGroupDTO;
import com.simple.blog.dto.LabelRelationDTO;
import com.simple.blog.service.LabelService;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelGroupVO;
import com.simple.blog.vo.LabelRelationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/queryLabelCache")
    @ControllerAspectAnnotation(description = "查询标签缓存")
    public CommonDTO<LabelGroupDTO> queryLabelCache() {
        CommonDTO<LabelGroupDTO> commonDTO = labelService.getLabelCache();
        return commonDTO;
    }

    @PostMapping("/insertLabelGroup")
    @ControllerAspectAnnotation(description = "插入标签分组名")
    public CommonDTO<LabelGroupDTO> insertLabelGroup(@RequestBody CommonVO<List<LabelGroupVO>> commonVO) {
        CommonDTO<LabelGroupDTO> commonDTO = labelService.saveLabelGroup(commonVO);
        return commonDTO;
    }

    @PostMapping("/insertLabelRelation")
    @ControllerAspectAnnotation(description = "插入标签关系")
    public CommonDTO<LabelRelationDTO> insertLabelRelation(@RequestBody CommonVO<List<LabelRelationVO>> commonVO) {
        CommonDTO<LabelRelationDTO> commonDTO = labelService.saveLabelRelation(commonVO);
        return commonDTO;
    }
}
