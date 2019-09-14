package com.simple.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by songning on 2019/9/14 2:01 PM
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "Mail")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Mail {
    /**
     * 邮件id
     */
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    @Column(name = "sender", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '邮件发送人'")
    private String sender;

    @Column(name = "recipient", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '邮件接收人'")
    private String recipient;

    @Column(name = "subject", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '邮件主题'")
    private String subject;
    /**
     * 邮件内容
     */
    @Column(name = "content", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '邮件内容'")
    private String content;
    /**
     * 发送时间
     */
    @Column(name = "sentDate", columnDefinition = "DATETIME NOT NULL COMMENT '标题'")
    private Date sentDate;
    /**
     * 抄送（多个邮箱则用逗号","隔开）
     */
    @Column(name = "cc", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '抄送'")
    private String cc;
    /**
     * 密送（多个邮箱则用逗号","隔开）
     */
    @Column(name = "bcc", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '密送'")
    private String bcc;
}
