package com.simple.blog.service;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.UsersDTO;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.UsersVO;

/**
 * @author songning
 * @date 2019/10/24
 * description
 */
public interface UsersService {

    CommonDTO<UsersDTO> isExist(CommonVO<UsersVO> commonVO);
}
