package com.simple.blog.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author sn
 */
@Data
public class BlogDTO {
    private Long id;

    private String title;

    private String summary;

    private Integer readTimes;

    private String kinds;

    private String content;

    private Date createTime;

    private Date updateTime;
}
