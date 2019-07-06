package com.simple.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author sn
 * 博客内容表
 */
@Data
@Entity
@Table(name = "BlogContent")
public class BlogContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", columnDefinition = "TEXT NOT NULL COMMENT '内容'")
    private String content;

    @Column(name = "createTime", columnDefinition = "DATETIME NOT NULL COMMENT '创建时间'")
    private Date createTime;

    @Column(name = "updateTime", columnDefinition = "DATETIME NOT NULL COMMENT '更新时间'")
    private Date updateTime;
}
