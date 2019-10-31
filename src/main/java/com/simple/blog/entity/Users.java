package com.simple.blog.entity;

import lombok.*;

import javax.persistence.*;

/**
 * @author songning
 * @date 2019/9/18
 * description
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "Users")
public class Users {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "username", columnDefinition = "VARCHAR(60) NOT NULL UNIQUE COMMENT '用户名'")
    private String username;

    @Column(name = "password", columnDefinition = "VARCHAR(60) NOT NULL COMMENT '密码'")
    private String password;

    @Column(name = "role", columnDefinition = "VARCHAR(20) NOT NULL COMMENT '权限'")
    private String role;
}
