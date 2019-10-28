package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.ImageDTO;
import com.simple.blog.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author songning
 * @date 2019/10/28
 * description
 */
@RestController
@Slf4j
@RequestMapping(value = "/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/save")
    @ControllerAspectAnnotation(description = "把上传的图片保存")
    public CommonDTO<ImageDTO> saveImages(@RequestParam("file") MultipartFile multipartFile) {
        CommonDTO<ImageDTO> commonDTO = imageService.saveImage(multipartFile);
        return commonDTO;
    }
}
