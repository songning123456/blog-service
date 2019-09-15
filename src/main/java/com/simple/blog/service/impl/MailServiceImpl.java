package com.simple.blog.service.impl;

import com.simple.blog.dto.MailDTO;
import com.simple.blog.service.MailService;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.MailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.util.Date;

/**
 * @author  songning on 2019/9/13 10:05 PM
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Override
    public void sendMail(CommonVO<MailVO> commonVO) throws Exception {
        checkMail(commonVO.getCondition());
        sendMimeMail(commonVO);
    }

    private void sendMimeMail(CommonVO<MailVO> commonVO) throws MessagingException {
        MailVO mailVO = commonVO.getCondition();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage());
        mailVO.setSender(getMailSendFrom());
        mimeMessageHelper.setFrom(mailVO.getSender());
        mimeMessageHelper.setTo(mailVO.getRecipient().split(","));
        mimeMessageHelper.setSubject(mailVO.getSubject());
        mimeMessageHelper.setText(mailVO.getContent());
        // 抄送
        if (!StringUtils.isEmpty(mailVO.getCc())) {
            mimeMessageHelper.setCc(mailVO.getCc().split(","));
        }
        //密送
        if (!StringUtils.isEmpty(mailVO.getBcc())) {
            mimeMessageHelper.setCc(mailVO.getBcc().split(","));
        }
        // 添加邮件附件
        if (mailVO.getMultipartFiles() != null) {
            for (MultipartFile multipartFile : mailVO.getMultipartFiles()) {
                mimeMessageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
            }
        }
        // 发送时间
        if (StringUtils.isEmpty(mailVO.getSentDate())) {
            mailVO.setSentDate(new Date());
            mimeMessageHelper.setSentDate(mailVO.getSentDate());
        }
        //正式发送邮件
        javaMailSender.send(mimeMessageHelper.getMimeMessage());

    }

    private void checkMail(MailVO mailVO) throws Exception {
        if (StringUtils.isEmpty(mailVO.getRecipient())) {
            throw new Exception("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mailVO.getSubject())) {
            throw new Exception("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mailVO.getContent())) {
            throw new Exception("邮件内容不能为空");
        }
    }

    /**
     * 保存邮件
     *
     * @param mailVO
     */
    private void saveMail(MailVO mailVO) {

    }

    /**
     * 获取邮件发件人
     *
     * @return
     */
    private String getMailSendFrom() {
        return javaMailSender.getJavaMailProperties().getProperty("from");
    }


}
