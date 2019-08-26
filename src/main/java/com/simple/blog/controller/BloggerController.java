package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.BloggerDTO;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.entity.Blogger;
import com.simple.blog.service.BloggerService;
import com.simple.blog.vo.BloggerVO;
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
@RequestMapping(value = "/blogger")
public class BloggerController {

    @Autowired
    private BloggerService bloggerService;

    @PostMapping("/insert")
    @ControllerAspectAnnotation(description = "插入登陆作者信息")
    public CommonDTO<BloggerDTO> insertBlogger(@RequestBody CommonVO<BloggerVO> commonVO) {
        bloggerService.saveBlogger(commonVO);
        return new CommonDTO<>();
    }

    @PostMapping("/get")
    @ControllerAspectAnnotation(description = "获取登陆信息")
    public CommonDTO<BloggerDTO> getBloggerInfo(@RequestBody CommonVO<BloggerVO> commonVO) {
        CommonDTO<BloggerDTO> commonDTO = bloggerService.getBlogger(commonVO);
        return commonDTO;
    }
}
