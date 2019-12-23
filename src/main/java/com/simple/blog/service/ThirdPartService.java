package com.simple.blog.service;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.ThirdPartDTO;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.ThirdPartVO;

/**
 * @author songning
 * @date 2019/12/23
 * description
 */
public interface ThirdPartService {
    CommonDTO<ThirdPartDTO> getGitHub(CommonVO<ThirdPartVO> commonVO);
}
