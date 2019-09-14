package com.simple.blog.service;

import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.MailVO;

/**
 * @author songning on 2019/9/14 3:29 PM
 */
public interface MailService {

    /**
     * @param commonVO
     * @throws Exception
     */
    void sendMail(CommonVO<MailVO> commonVO) throws Exception;
}
