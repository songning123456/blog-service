package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelDTO;
import com.simple.blog.service.LabelService;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/querySelected")
    @ControllerAspectAnnotation(description = "获取用户关注的标签")
    public CommonDTO<LabelDTO> getSelectedLabels() {
        CommonDTO<LabelDTO> commonDTO = labelService.getSelectedLabel();
        return commonDTO;
    }

    @PostMapping("/all")
    @ControllerAspectAnnotation(description = "获取所有的标签")
    public CommonDTO<LabelDTO> getAllLabels(@RequestBody CommonVO<LabelVO> vo) {
        CommonDTO<LabelDTO> commonDTO = labelService.getAllLabel(vo);
        return commonDTO;
    }

    @PostMapping("/statistic")
    @ControllerAspectAnnotation(description = "统计每种标签的关注度和文章数量")
    public CommonDTO<LabelDTO> statisticLabels(@RequestBody CommonVO<LabelVO> vo) {
        CommonDTO<LabelDTO> commonDTO = labelService.statisticLabel(vo);
        return commonDTO;
    }

    @PostMapping("/updateAttention")
    @ControllerAspectAnnotation(description = "更新关注状态")
    public CommonDTO<LabelDTO> updateAttentions(@RequestBody CommonVO<LabelVO> commonVO) {
        CommonDTO<LabelDTO> commonDTO = labelService.updateAttention(commonVO);
        return commonDTO;
    }

    @GetMapping("/getLabelConfig")
    @ControllerAspectAnnotation(description = "注册时,获取所有标签")
    public CommonDTO<LabelDTO> getAllLabelConfigs() {
        CommonDTO<LabelDTO> commonDTO = labelService.getAllLabelConfig();
        return commonDTO;
    }
}
