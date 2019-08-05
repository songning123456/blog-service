package com.simple.blog.service;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelGroupDTO;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelGroupVO;

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

    /**
     * 插入标签
     *
     * @param commonVO
     * @return
     */
    CommonDTO<LabelGroupDTO> saveLabelGroup(CommonVO<LabelGroupVO> commonVO);
}
