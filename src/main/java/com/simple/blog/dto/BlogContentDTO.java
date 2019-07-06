package com.simple.blog.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author sn
 */
@Data
public class BlogContentDTO {

    private Long id;

    private String content;

    private Date createTime;

    private Date updateTime;
}
