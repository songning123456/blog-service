package com.simple.blog.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author sn
 * 博主信息表
 */
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "Blogger")
public class Blogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '用户名'")
    private String userName;

    @Column(name = "password", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '密码'")
    private String password;

    @Column(name = "gender", columnDefinition = "INT NOT NULL COMMENT '性别'")
    private Integer gender;

    @Column(name = "age", columnDefinition = "INT NOT NULL COMMENT '年龄'")
    private Integer age;

    @Column(name = "profession", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '职业'")
    private String profession;

    @Column(name = "telephone", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '电话'")
    private String telephone;

    @Column(name = "email", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '邮箱'")
    private String email;

    @Column(name = "introduction", columnDefinition = "VARCHAR(255) NOT NULL COMMENT '介绍'")
    private String introduction;

    @Column(name = "createTime", columnDefinition = "DATETIME NOT NULL COMMENT '创建时间'")
    private Date createTime;

    @Column(name = "updateTime", columnDefinition = "DATETIME NOT NULL COMMENT '更新时间'")
    private Date updateTime;

}
