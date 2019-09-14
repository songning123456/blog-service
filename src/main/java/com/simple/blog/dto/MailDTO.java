package com.simple.blog.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author  songning on 2019/9/13 10:09 PM
 */
@Data
public class MailDTO {

    /**
     * 邮件发送人
     */
    private String from;

    /**
     * 邮件接收人（多个邮箱则用逗号","隔开）
     */
    private String to;

    /**
     * 邮件主题
     */
    private String subject;

    /**
     * 邮件内容
     */
    private String text;

    /**
     * 发送时间
     */
    private Date sentDate;

    /**
     * 抄送（多个邮箱则用逗号","隔开）
     */
    private String cc;

    /**
     * 密送（多个邮箱则用逗号","隔开）
     */
    private String bcc;

}
