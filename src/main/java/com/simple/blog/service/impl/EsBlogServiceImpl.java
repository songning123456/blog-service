package com.simple.blog.service.impl;

import com.fasterxml.jackson.databind.util.ClassUtil;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.EsBlogDTO;
import com.simple.blog.entity.EsBlog;
import com.simple.blog.repository.EsBlogRepository;
import com.simple.blog.service.EsBlogService;
import com.simple.blog.util.ClassConvertUtil;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.EsBlogVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author songning
 * @create 2019/8/15 8:16
 */
@Service
public class EsBlogServiceImpl implements EsBlogService {
    @Autowired
    private EsBlogRepository esBlogRepository;

    @Override
    public CommonDTO<EsBlogDTO> saveArticle(CommonVO<EsBlogVO> commonVO) {
        String title = commonVO.getCondition().getTitle();
        String content = commonVO.getCondition().getContent();
        String summary = commonVO.getCondition().getSummary();
        String kinds = commonVO.getCondition().getKinds();
        Integer readTimes = commonVO.getCondition().getReadTimes();
        String author = commonVO.getCondition().getAuthor();
        EsBlog esBlog = EsBlog.builder().title(title).content(content).summary(summary).kinds(kinds).readTimes(readTimes).author(author).updateTime(new Date()).build();
        esBlogRepository.save(esBlog);
        return new CommonDTO<>();
    }

    @Override
    public CommonDTO<EsBlogDTO> getAbstract(CommonVO<EsBlogVO> commonVO) {
        CommonDTO<EsBlogDTO> commonDTO = new CommonDTO<>();
        Integer recordStartNo = commonVO.getRecordStartNo();
        Integer pageRecordNum = commonVO.getPageRecordNum();
        String kinds = commonVO.getCondition().getKinds();
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(recordStartNo, pageRecordNum, sort);
        Page<EsBlog> blogPage = esBlogRepository.findAll(pageable);
        List<EsBlogDTO> esBlogDTOList = new ArrayList<>();
        ClassConvertUtil.populateList(blogPage.getContent(), esBlogDTOList, EsBlogDTO.class);
        commonDTO.setData(esBlogDTOList);
        commonDTO.setTotal(blogPage.getTotalElements());
        return commonDTO;
    }

    @Override
    public CommonDTO<EsBlogDTO> getContent(CommonVO<EsBlogVO> commonVO) {
        CommonDTO<EsBlogDTO> commonDTO = new CommonDTO<>();
        String id = commonVO.getCondition().getId();
        Optional<EsBlog> blog = esBlogRepository.findById(id);
        Integer readTimes = blog.get().getReadTimes();
        blog.get().setReadTimes(++readTimes);
        esBlogRepository.save(blog.get());
        EsBlogDTO esBlogDTO = new EsBlogDTO();
        ClassConvertUtil.populate(blog.get(), esBlogDTO);
        commonDTO.setData(Collections.singletonList(esBlogDTO));
        commonDTO.setTotal(1L);
        return commonDTO;
    }

    @Override
    public CommonDTO<EsBlogDTO> getHotArticle(CommonVO<EsBlogVO> commonVO) {
        CommonDTO<EsBlogDTO> commonDTO = new CommonDTO<>();
        String kinds = commonVO.getCondition().getKinds();
        Integer recordStartNo = commonVO.getRecordStartNo();
        Integer pageRecordNum = commonVO.getPageRecordNum();
        Sort sort = new Sort(Sort.Direction.DESC, "readTimes");
        Pageable pageable = PageRequest.of(recordStartNo, pageRecordNum, sort);
        List<EsBlog> esBlogList = esBlogRepository.findByKinds(kinds, pageable);
        List<EsBlogDTO> esBlogDTOList = new ArrayList<>();
        ClassConvertUtil.populateList(esBlogList, esBlogDTOList, EsBlogDTO.class);
        commonDTO.setData(esBlogDTOList);
        commonDTO.setTotal((long) esBlogDTOList.size());
        return commonDTO;
    }
}
