package com.simple.blog.service;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.SystemConfigDTO;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.SystemConfigVO;

/**
 * @author: songning
 * @date: 2019/10/7 11:55
 */
public interface SystemConfigService {

    CommonDTO<SystemConfigDTO> getSystemConfig(CommonVO<SystemConfigVO> commonVO);

    <T> CommonDTO<T> updateSystemConfig(CommonVO<SystemConfigVO> commonVO);

    <T> CommonDTO<T> saveSystemConfig(CommonVO<SystemConfigVO> commonVO);
}
