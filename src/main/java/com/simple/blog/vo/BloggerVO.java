package com.simple.blog.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author sn
 */

@Data
public class BloggerVO {
    private String id;

    private String author;

    private String realName;

    private String gender;

    private Integer age;

    private String profession;

    private String telephone;

    private String email;

    private String introduction;

    private String headPortrait;

    private String username;
}
