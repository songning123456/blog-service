package com.simple.blog.service.impl;

import com.simple.blog.dto.BlogDTO;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.feign.ElasticSearchFeignClient;
import com.simple.blog.service.BlogService;
import com.simple.blog.vo.BlogVO;
import com.simple.blog.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author songning
 * @create 2019/8/15 8:16
 */
@Service
public class EsBlogServiceImpl implements BlogService {

    @Autowired
    private ElasticSearchFeignClient elasticSearchFeignClient;

    @Override
    public CommonDTO<BlogDTO> saveArticle(CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = elasticSearchFeignClient.esInsertArticle(commonVO);
        return commonDTO;
    }

    @Override
    public CommonDTO<BlogDTO> getAbstract(CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = elasticSearchFeignClient.esQueryAbstract(commonVO);
        return commonDTO;
    }

    @Override
    public CommonDTO<BlogDTO> getContent(CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = elasticSearchFeignClient.esQueryContent(commonVO);
        return commonDTO;
    }

    @Override
    public CommonDTO<BlogDTO> getHotArticle(CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = elasticSearchFeignClient.esQueryHotArticle(commonVO);
        return commonDTO;
    }

    @Override
    public CommonDTO<BlogDTO> getHighlightArticle(CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = elasticSearchFeignClient.esQueryHighlightArticle(commonVO);
        return commonDTO;
    }
}
