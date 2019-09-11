package com.simple.blog.service.impl;

import com.fasterxml.jackson.databind.util.ClassUtil;
import com.simple.blog.dto.BlogDTO;
import com.simple.blog.dto.BloggerDTO;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.entity.Blogger;
import com.simple.blog.repository.BloggerRepository;
import com.simple.blog.service.BloggerService;
import com.simple.blog.util.ClassConvertUtil;
import com.simple.blog.util.MapConvertEntityUtil;
import com.simple.blog.vo.BloggerVO;
import com.simple.blog.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author sn
 */
@Service
public class BloggerServiceImpl implements BloggerService {

    @Autowired
    private BloggerRepository bloggerRepository;

    @Override
    public void saveBlogger(CommonVO<BloggerVO> commonVO) {
        BloggerVO bloggerVO = commonVO.getCondition();
        Blogger blogger = new Blogger();
        ClassConvertUtil.populate(bloggerVO, blogger);
        blogger.setUpdateTime(new Date());
        bloggerRepository.save(blogger);
    }

    @Override
    public CommonDTO<BloggerDTO> getBlogger(CommonVO<BloggerVO> commonVO) {
        CommonDTO<BloggerDTO> commonDTO = new CommonDTO<>();
        String author = commonVO.getCondition().getAuthor();
        List<Map<String, Object>> list = bloggerRepository.findByAuthorNative(author);
        BloggerDTO bloggerDTO = new BloggerDTO();
        try {
            bloggerDTO = (BloggerDTO) MapConvertEntityUtil.mapToEntity(BloggerDTO.class, list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        commonDTO.setData(Collections.singletonList(bloggerDTO));
        commonDTO.setTotal(1L);
        return commonDTO;
    }
}
