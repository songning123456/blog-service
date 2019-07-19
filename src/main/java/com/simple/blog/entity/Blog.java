package com.simple.blog.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author sn
 * 博客信息表
 */
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "Blog")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Blog {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    @Column(name = "title", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '标题'")
    private String title;

    @Column(name = "summary", columnDefinition = "VARCHAR(255)  NOT NULL COMMENT '摘要'")
    private String summary;

    @Column(name = "readTimes", columnDefinition = "INT NOT NULL default 0 COMMENT '阅读次数'")
    private Integer readTimes;

    @Column(name = "kinds", columnDefinition = "VARCHAR(255)  NOT NULL COMMENT '种类'")
    private String kinds;

    @Column(name = "content", columnDefinition = "TEXT  NOT NULL COMMENT '内容'")
    private String content;

    @CreatedDate
    @Column(name = "createTime", columnDefinition = "DATETIME NOT NULL COMMENT '创建时间'")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "updateTime", columnDefinition = "DATETIME NOT NULL COMMENT '更新时间'")
    private Date updateTime;

    public Blog() {

    }
}
