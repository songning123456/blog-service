package com.simple.blog.service.impl;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.HistoryDTO;
import com.simple.blog.entity.History;
import com.simple.blog.repository.BlogRepository;
import com.simple.blog.repository.HistoryRepository;
import com.simple.blog.service.HistoryService;
import com.simple.blog.util.ClassConvertUtil;
import com.simple.blog.util.CssStyleUtil;
import com.simple.blog.util.DateUtil;
import com.simple.blog.util.HttpServletRequestUtil;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.HistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author songning
 * @date 2019/10/22
 * description
 */
@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HttpServletRequestUtil httpServletRequestUtil;
    @Autowired
    private HistoryRepository historyRepository;
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public CommonDTO<HistoryDTO> insertHistory(CommonVO<HistoryVO> commonVO) {
        String title = commonVO.getCondition().getTitle();
        if (CommonConstant.READ_ARTICLE.equals(title)) {
            String articleId = commonVO.getCondition().getArticleId();
            String username = httpServletRequestUtil.getUsername();
            Map<String, Object> map = blogRepository.findByIdNative(articleId);
            title = title + " —— " + map.get("title");
            String time = DateUtil.dateToStr(new Date(), CommonConstant.DEFAULT_DATETIME_PATTERN);
            String description = CssStyleUtil.getBoldAndItalicFont(username) + " 提交于 " + CssStyleUtil.getBoldAndItalicFont(time);
            History history = History.builder().title(title).articleId(articleId).username(username).time(time).description(description).build();
            historyRepository.save(history);
        }
        return new CommonDTO<>();
    }

    @Override
    public CommonDTO<HistoryDTO> getHistory(CommonVO<HistoryVO> commonVO) {
        CommonDTO<HistoryDTO> commonDTO = new CommonDTO<>();
        Integer recordStartNo = commonVO.getRecordStartNo();
        Integer pageRecordNum = commonVO.getPageRecordNum();
        String username = httpServletRequestUtil.getUsername();
        Sort sort = new Sort(Sort.Direction.DESC, "time");
        Pageable pageable = PageRequest.of(recordStartNo, pageRecordNum, sort);
        Page<History> historyPage = historyRepository.findHistoryNative(username, pageable);
        List<History> list = historyPage.getContent();
        List<HistoryDTO> target = new ArrayList<>();
        ClassConvertUtil.populateList(list, target, HistoryDTO.class);
        commonDTO.setTotal((long) target.size());
        commonDTO.setData(target);
        return commonDTO;
    }
}
