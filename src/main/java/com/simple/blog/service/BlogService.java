package com.simple.blog.service;

import com.simple.blog.dto.BlogDTO;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.vo.BlogVO;
import com.simple.blog.vo.CommonVO;

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
}
