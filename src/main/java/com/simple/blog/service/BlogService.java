package com.simple.blog.service;

import com.simple.blog.dto.BlogDTO;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelStatisticDTO;
import com.simple.blog.vo.BlogVO;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelStatisticVO;

/**
 * @author sn
 */
public interface BlogService {
    /**
     * 新增文章
     *
     * @param commonVO
     * @return
     */
    CommonDTO<BlogDTO> saveArticle(CommonVO<BlogVO> commonVO);

    /**
     * 查询摘要等信息
     *
     * @param commonVO
     * @return
     */
    CommonDTO<BlogDTO> getAbstract(CommonVO<BlogVO> commonVO);

    /**
     * 获取文章内容
     *
     * @param commonVO
     * @return
     */
    CommonDTO<BlogDTO> getContent(CommonVO<BlogVO> commonVO);

    /**
     * 查询热门文章
     *
     * @param commonVO
     * @return
     */
    CommonDTO<BlogDTO> getHotArticle(CommonVO<BlogVO> commonVO);

    /**
     * 高亮搜索文章内容
     *
     * @param commonVO
     * @return
     */
    CommonDTO<BlogDTO> getHighlightArticle(CommonVO<BlogVO> commonVO);

    Long statisticLabel(CommonVO<LabelStatisticVO> vo);
}
