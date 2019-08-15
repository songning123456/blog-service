package com.simple.blog.controller;

import com.simple.blog.service.EsBlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
}
