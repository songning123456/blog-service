package com.simple.blog.service;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.EsBlogDTO;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.EsBlogVO;

/**
 * @Author songning
 * @create 2019/8/15 8:16
 */
public interface EsBlogService {
    /**
     * 新增文章
     *
     * @param commonVO
     * @return
     */
    CommonDTO<EsBlogDTO> saveArticle(CommonVO<EsBlogVO> commonVO);

    /**
     * 查询摘要等信息
     *
     * @param commonVO
     * @return
     */
    CommonDTO<EsBlogDTO> getAbstract(CommonVO<EsBlogVO> commonVO);

    /**
     * 获取文章内容
     *
     * @param commonVO
     * @return
     */
    CommonDTO<EsBlogDTO> getContent(CommonVO<EsBlogVO> commonVO);

    /**
     * 查询热门文章
     *
     * @param commonVO
     * @return
     */
    CommonDTO<EsBlogDTO> getHotArticle(CommonVO<EsBlogVO> commonVO);

    /**
     * 删除所有文章
     *
     * @return
     */
//    CommonDTO<EsBlogDTO> deleteAllArticle();
}
