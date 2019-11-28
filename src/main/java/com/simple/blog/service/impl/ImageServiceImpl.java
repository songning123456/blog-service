package com.simple.blog.service.impl;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.ImageDTO;
import com.simple.blog.service.ImageService;
import com.simple.blog.util.FileUtil;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.ImageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
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
    public CommonDTO<ImageDTO> saveImage(MultipartFile multipartFile, String dir) {
        CommonDTO<ImageDTO> commonDTO = new CommonDTO<>();
        if (StringUtils.isEmpty(dir)) {
            dir = "other";
        }
        String imageFile = System.getProperty("user.home") + File.separator + path + File.separator + dir;
        String imageName = UUID.randomUUID() + "." + Objects.requireNonNull(multipartFile.getOriginalFilename()).split("\\.")[1];
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

    @Override
    public <T> CommonDTO<T> deleteImage(CommonVO<ImageVO> commonVO) {
        CommonDTO<T> commonDTO = new CommonDTO<>();
        String imageName = commonVO.getCondition().getImageName();
        String imagePath = System.getProperty("user.home") + File.separator + path + File.separator + "avatar";
        String filename = imagePath + File.separator + imageName;
        File file = new File(filename);
        if (file.exists()) {
            boolean result = file.delete();
            if (!result) {
                try {
                    throw new Exception("删除失败");
                } catch (Exception e) {
                    e.printStackTrace();
                    commonDTO.setStatus(300);
                    commonDTO.setMessage("删除失败");
                }
            }
        } else {
            try {
                throw new Exception("文件不存在");
            } catch (Exception e) {
                e.printStackTrace();
                commonDTO.setStatus(300);
                commonDTO.setMessage("文件不存在");
            }
        }
        return commonDTO;
    }
}
