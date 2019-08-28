package com.simple.blog.controller;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.StatisticDTO;
import com.simple.blog.service.StatisticService;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.StatisticVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songning
 * @date 2019/8/27
 * description
 */
@RestController
@Slf4j
@RequestMapping("/statistic")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @PostMapping("/hadoop")
    public CommonDTO<StatisticDTO> getHadoop(@RequestBody CommonVO<StatisticVO> commonVO) {
        CommonDTO<StatisticDTO> commonDTO = statisticService.getStatisticResult(commonVO);
        return commonDTO;
    }
}
