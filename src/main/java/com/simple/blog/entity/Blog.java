package com.simple.blog.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author sn
 * 博客信息表
 */
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "Blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR(255) COMMENT '标题'")
    private String title;

    @Column(name = "blogInfo", columnDefinition = "VARCHAR(255) COMMENT '博客信息'")
    private String blogInfo;

    @Column(name = "readTimes", columnDefinition = "INT COMMENT '阅读次数'")
    private Integer readTimes;

    @Column(name = "kinds", columnDefinition = "VARCHAR(255) COMMENT '种类'")
    private String kinds;

    @Column(name = "content", columnDefinition = "TEXT COMMENT '内容'")
    private String content;

    @CreatedDate
    @Column(name = "createTime", columnDefinition = "DATETIME NOT NULL COMMENT '创建时间'")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "updateTime", columnDefinition = "DATETIME NOT NULL COMMENT '更新时间'")
    private Date updateTime;
}
