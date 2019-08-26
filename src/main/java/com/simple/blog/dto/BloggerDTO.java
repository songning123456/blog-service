package com.simple.blog.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author sn
 */

@Data
public class BloggerDTO {
    private String id;

    private String userName;

    private String password;

    private String gender;

    private Integer age;

    private String profession;

    private String telephone;

    private String email;

    private String introduction;

    private Date updateTime;

    private String headPortrait;
}
