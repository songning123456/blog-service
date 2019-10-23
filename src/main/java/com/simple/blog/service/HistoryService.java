package com.simple.blog.service;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.HistoryDTO;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.HistoryVO;

/**
 * @author songning
 * @date 2019/10/22
 * description
 */
public interface HistoryService {

    /**
     * 插入记录信息
     *
     * @param commonVO
     * @return
     */
    CommonDTO<HistoryDTO> insertHistory(CommonVO<HistoryVO> commonVO);

    /**
     * 获取个人信息
     *
     * @param commonVO
     * @return
     */
    CommonDTO<HistoryDTO> getHistory(CommonVO<HistoryVO> commonVO);
}
