package com.simple.blog.service;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.RegisterDTO;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.RegisterVO;

/**
 * @author songning
 * @date 2019/11/1
 * description
 */
public interface RegisterService {

    CommonDTO<RegisterDTO> registerAll(CommonVO<RegisterVO> commonVO);
}
