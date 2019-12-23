package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.ThirdPartDTO;
import com.simple.blog.service.ThirdPartService;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.ThirdPartVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songning
 * @date 2019/12/23
 * description
 */
@RestController
@Slf4j
@RequestMapping("/thirdPart")
public class ThirdPartController {

    @Autowired
    private ThirdPartService thirdPartService;

    @PostMapping("/gitHub")
    @ControllerAspectAnnotation(description = "获取github用户数据")
    public CommonDTO<ThirdPartDTO> getGitHubs(@RequestBody CommonVO<ThirdPartVO> commonVO) {
        CommonDTO<ThirdPartDTO> commonDTO = thirdPartService.getGitHub(commonVO);
        return commonDTO;
    }
}
