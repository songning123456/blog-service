package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.BlogDTO;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.util.DataBaseUtil;
import com.simple.blog.vo.BlogVO;
import com.simple.blog.vo.CommonVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sn
 */
@RestController
@Slf4j
@RequestMapping(value = "/blog")
public class BlogController {

    @Autowired
    private DataBaseUtil dataBaseUtil;

    @PostMapping("/insertArticle")
    @ControllerAspectAnnotation(description = "新增文章")
    public CommonDTO<BlogDTO> insertArticle(@RequestBody CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = dataBaseUtil.getDataBase().saveArticle(commonVO);
        return commonDTO;
    }

    @PostMapping("/queryAbstract")
    @ControllerAspectAnnotation(description = "查询摘要等信息")
    public CommonDTO<BlogDTO> queryAbstract(@RequestBody CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = dataBaseUtil.getDataBase().getAbstract(commonVO);
        return commonDTO;
    }

    @PostMapping("/queryByUser")
    @ControllerAspectAnnotation(description = "根据作者查询-我写过的")
    public CommonDTO<BlogDTO> queryByAuthors(@RequestBody CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = dataBaseUtil.getDataBase().getByAuthor(commonVO);
        return commonDTO;
    }

    @PostMapping("/queryByLove")
    @ControllerAspectAnnotation(description = "根据我赞过的")
    public CommonDTO<BlogDTO> queryByLoves(@RequestBody CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = dataBaseUtil.getDataBase().getByLove(commonVO);
        return commonDTO;
    }

    @PostMapping("/queryContent")
    @ControllerAspectAnnotation(description = "查询文章内容")
    public CommonDTO<BlogDTO> queryContent(@RequestBody CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = dataBaseUtil.getDataBase().getContent(commonVO);
        return commonDTO;
    }

    @PostMapping("/queryHotArticle")
    @ControllerAspectAnnotation(description = "查询热门文章")
    public CommonDTO<BlogDTO> queryHotArticle(@RequestBody CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = dataBaseUtil.getDataBase().getHotArticle(commonVO);
        return commonDTO;
    }

    @PostMapping("/searchArticle")
    @ControllerAspectAnnotation(description = "高亮搜索文章")
    public CommonDTO<BlogDTO> queryHighlightArticle(@RequestBody CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = dataBaseUtil.getDataBase().getHighlightArticle(commonVO);
        return commonDTO;
    }

    @PostMapping("/delete")
    @ControllerAspectAnnotation(description = "删除写过的文章")
    public <T> CommonDTO<T> deleteWrittenBlogs(@RequestBody CommonVO<BlogVO> commonVO) {
        CommonDTO<T> commonDTO = dataBaseUtil.getDataBase().deleteWrittenBlog(commonVO);
        return commonDTO;
    }
}
