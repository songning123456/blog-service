package com.simple.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "BlogCategory")
public class BlogCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    @Column(name = "blogId", columnDefinition = "BIGINT NOT NULL COMMENT '博客Id'")
    private Long blogId;

    @Column(name = "createTime", columnDefinition = "DATETIME NOT NULL COMMENT '创建时间'")
    private Date createTime;

    @Column(name = "updateTime", columnDefinition = "DATETIME NOT NULL COMMENT '更新时间'")
    private Date updateTime;
}
