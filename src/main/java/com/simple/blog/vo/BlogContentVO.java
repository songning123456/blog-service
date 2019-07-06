package com.simple.blog.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author sn
 */
@Data
public class BlogContentVO {

    private Long id;

    private String content;

    private Date createTime;

    private Date updateTime;
}
