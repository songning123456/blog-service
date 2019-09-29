package com.simple.blog.service.impl;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.StatisticDTO;
import com.simple.blog.feign.ElasticSearchFeignClient;
import com.simple.blog.service.StatisticService;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.StatisticVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author songning
 * @date 2019/8/27
 * description
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private ElasticSearchFeignClient elasticSearchFeignClient;

    @Override
    public CommonDTO<StatisticDTO> getStatisticResult(CommonVO<StatisticVO> commonVO) {
        CommonDTO<StatisticDTO> commonDTO = elasticSearchFeignClient.esStatistic(commonVO);
        return commonDTO;
    }
}
