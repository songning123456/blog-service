package com.simple.blog.service.impl;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.ImageDTO;
import com.simple.blog.service.ImageService;
import com.simple.blog.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

/**
 * @author songning
 * @date 2019/10/28
 * description
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Value("${blog.image.path}")
    private String path;

    @Override
    public CommonDTO<ImageDTO> saveImage(MultipartFile multipartFile) {
        CommonDTO<ImageDTO> commonDTO = new CommonDTO<>();
        String imageFile = System.getProperty("user.home") + File.separator + path;
        String imageName = UUID.randomUUID() + ".jpg";
        String imageSrc = imageFile + File.separator + imageName;
        try {
            InputStream inputStream = multipartFile.getInputStream();
            if (FileUtil.mkDirs(imageFile)) {
                FileUtil.streamToImage(imageSrc, inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageDTO imageDTO = ImageDTO.builder().imageSrc(imageSrc).build();
        commonDTO.setData(Collections.singletonList(imageDTO));
        commonDTO.setTotal(1L);
        return commonDTO;
    }
}
