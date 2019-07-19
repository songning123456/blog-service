package com.simple.blog.service.impl;

import com.simple.blog.dto.BlogDTO;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.entity.Blog;
import com.simple.blog.repository.BlogRepository;
import com.simple.blog.service.BlogService;
import com.simple.blog.util.ClassConvertUtil;
import com.simple.blog.vo.BlogVO;
import com.simple.blog.vo.CommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sn
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public CommonDTO<BlogDTO> saveArticle(CommonVO<BlogVO> commonVO) {
        String title = commonVO.getCondition().getTitle();
        String content = commonVO.getCondition().getContent();
        String summary = commonVO.getCondition().getSummary();
        String kinds = commonVO.getCondition().getKinds();
        Integer readTimes = commonVO.getCondition().getReadTimes();
        Blog blog = Blog.builder().title(title).kinds(kinds).summary(summary).content(content).readTimes(readTimes).build();
        blogRepository.save(blog);
        return new CommonDTO<>();
    }

    @Override
    public CommonDTO<BlogDTO> getAbstract(CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = new CommonDTO<>();
        Integer recordStartNo = commonVO.getRecordStartNo();
        Integer pageRecordNum = commonVO.getPageRecordNum();
        String kinds = commonVO.getCondition().getKinds();
        Sort sort = new Sort(Sort.Direction.DESC, "update_time");
        Pageable pageable = PageRequest.of(recordStartNo, pageRecordNum, sort);
        Page<Object[]> blogPage = blogRepository.findAbstract(kinds, pageable);
        List<Object[]> src = blogPage.getContent();
        Long total = blogPage.getTotalElements();
        List<BlogDTO> target = new ArrayList<>();
        try {
            ClassConvertUtil.castEntity(src, target, BlogDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        commonDTO.setData(target);
        commonDTO.setTotal(total);
        return commonDTO;
    }
}
