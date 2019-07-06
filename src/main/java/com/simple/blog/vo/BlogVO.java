package com.simple.blog.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author sn
 */
@Data
public class BlogVO {
    private Long id;

    private String title;

    private String blogInfo;

    private Integer readTimes;

    private String kinds;

    private String content;

    private Date createTime;

    private Date updateTime;
}
