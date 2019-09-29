package com.simple.blog.feign;

import com.simple.blog.dto.BlogDTO;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.vo.BlogVO;
import com.simple.blog.vo.CommonVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author songning
 * @date 2019/9/29
 * description
 */
@FeignClient(url = "${blog.es.url}", name = "esServerUrl")
public interface ElasticSearchFeignClient {

    /**
     * 新增文章
     *
     * @param commonVO
     * @return
     */
    @RequestMapping(value = "/es/blog/insertArticle")
    CommonDTO<BlogDTO> esInsertArticle(CommonVO<BlogVO> commonVO);

    /**
     * 查询摘要等信息
     *
     * @param commonVO
     * @return
     */
    @RequestMapping(value = "/es/blog/queryAbstract")
    CommonDTO<BlogDTO> esQueryAbstract(CommonVO<BlogVO> commonVO);

    /**
     * 查询文章内容
     *
     * @param commonVO
     * @return
     */
    @RequestMapping(value = "/es/blog/queryContent")
    CommonDTO<BlogDTO> esQueryContent(CommonVO<BlogVO> commonVO);

    /**
     * 查询热门文章
     *
     * @param commonVO
     * @return
     */
    @RequestMapping(value = "/es/blog/queryHotArticle")
    CommonDTO<BlogDTO> esQueryHotArticle(CommonVO<BlogVO> commonVO);

    /**
     * 高亮搜索文章
     *
     * @param commonVO
     * @return
     */
    @RequestMapping(value = "/es/blog/queryHighlightArticle")
    CommonDTO<BlogDTO> esQueryHighlightArticle(CommonVO<BlogVO> commonVO);

}
