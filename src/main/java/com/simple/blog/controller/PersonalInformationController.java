package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.PersonalInformationDTO;
import com.simple.blog.service.PersonalInformationService;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.PersonalInformationVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author songning on 2019/8/25 3:29 PM
 */
@RestController
@Slf4j
@RequestMapping(value = "/personalInfo")
public class PersonalInformationController {

    @Autowired
    private PersonalInformationService personalInformationService;

    @PostMapping("/insertPersonalInfo")
    @ControllerAspectAnnotation(description = "插入个人信息")
    public <T> CommonDTO<T> insertPersonalInfo(@RequestBody CommonVO<List<PersonalInformationVO>> commonVO) {
        CommonDTO commonDTO = personalInformationService.savePersonalInfo(commonVO);
        return commonDTO;
    }

    @PostMapping("/getPersonalInfo")
    @ControllerAspectAnnotation(description = "其他人获取作者信息")
    public CommonDTO<PersonalInformationDTO> acquirePersonalInfo(@RequestBody CommonVO<PersonalInformationVO> commonVO) {
        CommonDTO<PersonalInformationDTO> commonDTO = personalInformationService.getPersonalInfo(commonVO);
        return commonDTO;
    }

    @PostMapping("/getMyInfo")
    @ControllerAspectAnnotation(description = "获取个人信息")
    public CommonDTO<PersonalInformationDTO> getMyInfos(@RequestBody CommonVO<PersonalInformationVO> commonVO) {
        CommonDTO<PersonalInformationDTO> commonDTO = personalInformationService.getMyInfo(commonVO);
        return commonDTO;
    }
}
