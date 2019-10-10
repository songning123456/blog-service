package com.simple.blog.service;


import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelConfigDTO;
import com.simple.blog.dto.LabelRelationDTO;
import com.simple.blog.dto.LabelStatisticDTO;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelStatisticVO;

/**
 * @Author songning
 * @create 2019/7/31 18:01
 */
public interface LabelService {
    CommonDTO<LabelRelationDTO> getSelectedLabel();

    CommonDTO<LabelConfigDTO> getAllLabel();

    CommonDTO<LabelStatisticDTO> statisticLabel(CommonVO<LabelStatisticVO> commonVO);
}
