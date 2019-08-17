package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.EsBlogDTO;
import com.simple.blog.service.EsBlogService;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.EsBlogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author songning
 * @create 2019/8/15 8:22
 */
@RestController
@Slf4j
@RequestMapping(value = "/es/blog")
public class EsBlogController {

    @Autowired
    private EsBlogService esBlogService;

    @PostMapping("/insertArticle")
    @ControllerAspectAnnotation(description = "新增文章")
    public CommonDTO<EsBlogDTO> insertArticle(@RequestBody CommonVO<EsBlogVO> commonVO) {
        CommonDTO<EsBlogDTO> commonDTO = esBlogService.saveArticle(commonVO);
        return commonDTO;
    }

    @PostMapping("/queryAbstract")
    @ControllerAspectAnnotation(description = "查询摘要等信息")
    public CommonDTO<EsBlogDTO> queryAbstract(@RequestBody CommonVO<EsBlogVO> commonVO) {
        CommonDTO<EsBlogDTO> commonDTO = esBlogService.getAbstract(commonVO);
        return commonDTO;
    }

    @PostMapping("/queryContent")
    @ControllerAspectAnnotation(description = "查询文章内容")
    public CommonDTO<EsBlogDTO> queryContent(@RequestBody CommonVO<EsBlogVO> commonVO) {
        CommonDTO<EsBlogDTO> commonDTO = esBlogService.getContent(commonVO);
        return commonDTO;
    }

    @PostMapping("/queryHotArticle")
    @ControllerAspectAnnotation(description = "查询热门文章")
    public CommonDTO<EsBlogDTO> queryHotArticle(@RequestBody CommonVO<EsBlogVO> commonVO) {
        CommonDTO<EsBlogDTO> commonDTO = esBlogService.getHotArticle(commonVO);
        return commonDTO;
    }


}
