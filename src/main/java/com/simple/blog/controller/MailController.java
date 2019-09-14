package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.MailDTO;
import com.simple.blog.service.MailService;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.MailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author songning on 2019/9/14 3:37 PM
 */

@RestController
@Slf4j
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @PostMapping("/send")
    @ControllerAspectAnnotation(description = "发送邮件")
    public CommonDTO<MailDTO> sendMail(@RequestBody CommonVO<MailVO> commonVO, MultipartFile[] files) throws Exception {
        commonVO.getCondition().setMultipartFiles(files);
        mailService.sendMail(commonVO);
        return new CommonDTO<>();
    }
}
