package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelConfigDTO;
import com.simple.blog.dto.LabelRelationDTO;
import com.simple.blog.dto.LabelStatisticDTO;
import com.simple.blog.service.LabelService;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelStatisticVO;
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

    @GetMapping("/all")
    @ControllerAspectAnnotation(description = "获取所有的标签")
    public CommonDTO<LabelConfigDTO> getAllLabels() {
        CommonDTO<LabelConfigDTO> commonDTO = labelService.getAllLabel();
        return commonDTO;
    }

    @PostMapping("/statistic")
    @ControllerAspectAnnotation(description = "统计每种标签的关注度和文章数量")
    public CommonDTO<LabelStatisticDTO> statisticLabels(@RequestBody CommonVO<LabelStatisticVO> vo) {
        CommonDTO<LabelStatisticDTO> commonDTO = labelService.statisticLabel(vo);
        return commonDTO;
    }

    @PostMapping("/updateAttention")
    @ControllerAspectAnnotation(description = "更新关注状态")
    public CommonDTO<LabelStatisticDTO> updateAttentions(@RequestBody CommonVO<LabelStatisticVO> commonVO) {
        CommonDTO<LabelStatisticDTO> commonDTO = labelService.updateAttention(commonVO);
        return commonDTO;
    }
}
