package com.simple.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author sn
 * 博客信息表
 */
@Data
@Entity
@Table(name = "Blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '标题'")
    private String title;

    @Column(name = "blogInfo", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '博客信息'")
    private String blogInfo;

    @Column(name = "readTimes", columnDefinition = "INT NOT NULL COMMENT '阅读次数'")
    private Integer readTimes;

    @Column(name = "kinds", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '种类'")
    private String kinds;

    @Column(name = "content", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '内容'")
    private String content;

    @Column(name = "createTime", columnDefinition = "DATETIME NOT NULL COMMENT '创建时间'")
    private Date createTime;

    @Column(name = "updateTime", columnDefinition = "DATETIME NOT NULL COMMENT '更新时间'")
    private Date updateTime;
}
