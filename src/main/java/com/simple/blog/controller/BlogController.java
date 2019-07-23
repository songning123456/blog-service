package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.BlogDTO;
import com.simple.blog.dto.BloggerDTO;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.service.BlogService;
import com.simple.blog.vo.BlogVO;
import com.simple.blog.vo.CommonVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sn
 */
@RestController
@Slf4j
@RequestMapping(value = "/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping("/insertArticle")
    @ControllerAspectAnnotation(description = "新增文章")
    public CommonDTO<BlogDTO> insertArticle(@RequestBody CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = blogService.saveArticle(commonVO);
        return commonDTO;
    }

    @PostMapping("/queryAbstract")
    @ControllerAspectAnnotation(description = "查询摘要等信息")
    public CommonDTO<BlogDTO> queryAbstract(@RequestBody CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = blogService.getAbstract(commonVO);
        return commonDTO;
    }

    @PostMapping("/queryContent")
    @ControllerAspectAnnotation(description = "查询文章内容")
    public CommonDTO<BlogDTO> queryContent(@RequestBody CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = blogService.getContent(commonVO);
        return commonDTO;
    }
}
