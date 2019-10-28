package com.simple.blog.service;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.ImageDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author songning
 * @date 2019/10/28
 * description
 */
public interface ImageService {

    /**
     *
     * @return
     */
    CommonDTO<ImageDTO> saveImage(MultipartFile multipartFile);
}
