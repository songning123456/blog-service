package com.simple.blog.service;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelGroupDTO;
import com.simple.blog.dto.LabelRelationDTO;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelGroupVO;
import com.simple.blog.vo.LabelRelationVO;

import java.util.List;
import java.util.Map;

/**
 * @Author songning
 * @create 2019/7/31 18:01
 */
public interface LabelService {
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
    CommonDTO<LabelGroupDTO> saveLabelGroup(CommonVO<List<LabelGroupVO>> commonVO);

    /**
     * @param commonVO
     * @return
     */
    CommonDTO<LabelRelationDTO> saveLabelRelation(CommonVO<List<LabelRelationVO>> commonVO);

    /**
     * 获取分组缓存
     *
     * @return
     */
    Map<String, Object> getGroupCache();

    /**
     * 获取所有的labelName
     *
     * @return
     */
    CommonDTO<LabelRelationDTO> getAllLabelName();
}
