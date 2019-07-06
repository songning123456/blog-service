package com.simple.blog.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author sn
 */

@Data
public class BloggerDTO {
    private Long id;

    private String userName;

    private String password;

    private Integer gender;

    private Integer age;

    private String profession;

    private String telephone;

    private String email;

    private String introduction;

    private Date createTime;

    private Date updateTime;
}
