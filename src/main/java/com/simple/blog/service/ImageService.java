package com.simple.blog.service;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.ImageDTO;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.ImageVO;
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
    CommonDTO<ImageDTO> saveImage(MultipartFile multipartFile, String dir);

    CommonDTO<ImageDTO> operateAlbum(MultipartFile multipartFile, String dir);

    CommonDTO<ImageDTO> getAlbum(CommonVO<ImageVO> commonVO);

    <T> CommonDTO<T> deleteImage(CommonVO<ImageVO> commonVO);
}
