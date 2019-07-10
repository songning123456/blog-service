package com.simple.blog.service.impl;

import com.simple.blog.dto.BlogDTO;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.entity.Blog;
import com.simple.blog.repository.BlogRepository;
import com.simple.blog.service.BlogService;
import com.simple.blog.vo.BlogVO;
import com.simple.blog.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sn
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public CommonDTO<BlogDTO> saveArticle(CommonVO<BlogVO> commonVO) {
        String content = commonVO.getCondition().getContent();
        Blog blog = new Blog();
        blog.setContent(content);
        blogRepository.save(blog);
        return new CommonDTO<>();
    }
}
