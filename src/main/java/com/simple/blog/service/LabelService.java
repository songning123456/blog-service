package com.simple.blog.service;


import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelDTO;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelVO;

import java.util.List;

/**
 * @Author songning
 * @create 2019/7/31 18:01
 */
public interface LabelService {
    CommonDTO<LabelDTO> getSelectedLabel();

    CommonDTO<LabelDTO> getAllLabel(CommonVO<LabelVO> vo);

    CommonDTO<LabelDTO> statisticLabel(CommonVO<LabelVO> commonVO);

    CommonDTO<LabelDTO> updateAttention(CommonVO<LabelVO> commonVO);

    CommonDTO<LabelDTO> getAllLabelConfig();
}
