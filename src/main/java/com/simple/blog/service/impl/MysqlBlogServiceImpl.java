package com.simple.blog.service.impl;

import com.simple.blog.dto.BlogDTO;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelStatisticDTO;
import com.simple.blog.entity.Blog;
import com.simple.blog.repository.BlogRepository;
import com.simple.blog.service.BlogService;
import com.simple.blog.util.MapConvertEntityUtil;
import com.simple.blog.vo.BlogVO;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelStatisticVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author sn
 */
@Service
public class MysqlBlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public CommonDTO<BlogDTO> saveArticle(CommonVO<BlogVO> commonVO) {
        String title = commonVO.getCondition().getTitle();
        String content = commonVO.getCondition().getContent();
        String summary = commonVO.getCondition().getSummary();
        String kinds = commonVO.getCondition().getKinds();
        Integer readTimes = commonVO.getCondition().getReadTimes();
        String author = commonVO.getCondition().getAuthor();
        Blog blog = Blog.builder().title(title).kinds(kinds).summary(summary).author(author).content(content).updateTime(new Date()).readTimes(readTimes).build();
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
        Page<Map<String, Object>> blogPage = blogRepository.findAbstract(kinds, pageable);
        List<Map<String, Object>> src = blogPage.getContent();
        Long total = blogPage.getTotalElements();
        List<BlogDTO> target = new ArrayList<>();
        for (Map<String, Object> map : src) {
            try {
                BlogDTO blogDTO = (BlogDTO) MapConvertEntityUtil.mapToEntity(BlogDTO.class, map);
                target.add(blogDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        commonDTO.setData(target);
        commonDTO.setTotal(total);
        return commonDTO;
    }

    @Override
    public CommonDTO<BlogDTO> getContent(CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = new CommonDTO<>();
        String id = commonVO.getCondition().getId();
        Map<String, Object> blog = blogRepository.findByIdNative(id);
        Integer readTimes = (Integer) blog.get("readTimes");
        blogRepository.updateReadTimes(id, ++readTimes);
        BlogDTO blogDTO = null;
        try {
            blogDTO = (BlogDTO) MapConvertEntityUtil.mapToEntity(BlogDTO.class, blog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        commonDTO.setData(Collections.singletonList(blogDTO));
        commonDTO.setTotal(1L);
        return commonDTO;
    }

    @Override
    public CommonDTO<BlogDTO> getHotArticle(CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = new CommonDTO<>();
        String kinds = commonVO.getCondition().getKinds();
        List<Map<String, Object>> list = blogRepository.findHotArticle(kinds);
        List<BlogDTO> blogDTOS = new ArrayList<>();
        list.forEach(item -> {
            try {
                BlogDTO blogDTO = (BlogDTO) MapConvertEntityUtil.mapToEntity(BlogDTO.class, item);
                blogDTOS.add(blogDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        commonDTO.setData(blogDTOS);
        return commonDTO;
    }

    @Override
    public CommonDTO<BlogDTO> getHighlightArticle(CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = new CommonDTO<>();
        String content = commonVO.getCondition().getContent();
        List<Map<String, Object>> list = blogRepository.findByLikeContentNative(content);
        List<BlogDTO> blogDTOS = new ArrayList<>();
        list.forEach(item -> {
            String id = (String) item.get("id");
            String title = (String) item.get("title");
            String author = (String) item.get("author");
            Date updateTime = (Date) item.get("updateTime");
            String txt = (String) item.get("content");
            List<String> searchResult = this.matchPattern(txt, content);
            BlogDTO blogDTO = BlogDTO.builder().id(id).title(title).author(author).updateTime(updateTime).content(txt).searchResult(searchResult).build();
            blogDTOS.add(blogDTO);
        });
        commonDTO.setData(blogDTOS);
        commonDTO.setTotal((long) blogDTOS.size());
        return commonDTO;
    }

    @Override
    public Long statisticLabel(CommonVO<LabelStatisticVO> vo) {
        String labelName = vo.getCondition().getLabelName();
        Long articleTotal = blogRepository.countAllByKinds(labelName);
        return articleTotal;
    }

    private List<String> matchPattern(String content, String regex) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        int len = content.length();
        while (matcher.find()) {
            StringBuilder stringBuilder = new StringBuilder();
            int start = matcher.start();
            int end = matcher.end();
            int startBefore, endAfter;
            if (start > 8) {
                startBefore = start - 8;
            } else {
                startBefore = 0;
            }
            if (end + 8 < len) {
                endAfter = end + 8;
            } else {
                endAfter = len;
            }
            String text1 = content.substring(startBefore, start);
            String text2 = "<span style='color: #ffa500;font-weight: bold;font-size: 16px;'>" + matcher.group() + "</span>";
            String text3 = content.substring(end + 1, endAfter);
            result.add(stringBuilder.append("...").append(text1).append(text2).append(text3).append("...").toString());
        }
        return result;
    }
}