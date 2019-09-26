package com.simple.blog.service;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LikeTagDTO;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LikeTagVO;

/**
 * @author songning
 * @date 2019/9/26
 * description
 */
public interface LikeTagService {

    CommonDTO<LikeTagDTO> getTag(CommonVO<LikeTagVO> commonVO);

    CommonDTO<LikeTagDTO> updateTag(CommonVO<LikeTagVO> commonVO);
}
