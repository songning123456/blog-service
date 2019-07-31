package com.simple.blog.service;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelGroupDTO;

/**
 * @Author songning
 * @create 2019/7/31 18:01
 */
public interface LabelGroupService {
    /**
     * 获取label
     *
     * @return
     */
    CommonDTO<LabelGroupDTO> getLabelCache();
}
