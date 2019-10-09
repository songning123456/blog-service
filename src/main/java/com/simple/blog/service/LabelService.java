package com.simple.blog.service;


import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelConfigDTO;
import com.simple.blog.dto.LabelRelationDTO;

/**
 * @Author songning
 * @create 2019/7/31 18:01
 */
public interface LabelService {
    CommonDTO<LabelRelationDTO> getSelectedLabel();

    CommonDTO<LabelConfigDTO> getAllLabel();
}
