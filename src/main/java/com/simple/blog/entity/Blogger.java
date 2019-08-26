package com.simple.blog.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @author sn
 * 博主信息表
 */
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Data
@Entity
@Table(name = "Blogger")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Blogger {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    @Column(name = "userName", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '用户名'")
    private String userName;

    @Column(name = "password", columnDefinition = "VARCHAR(64) NOT NULL COMMENT '密码'")
    private String password;

    @Column(name = "gender", columnDefinition = "VARCHAR(4) NOT NULL COMMENT '性别'")
    private String gender;

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

    @Column(name = "updateTime", columnDefinition = "DATETIME NOT NULL COMMENT '更新时间'")
    private Date updateTime;

    @Column(name = "headPortrait", columnDefinition = "VARCHAR(255) COMMENT '头像'")
    private String headPortrait;
}
