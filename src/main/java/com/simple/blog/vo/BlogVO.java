package com.simple.blog.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author sn
 */
@Data
public class BlogVO {
    private String id;

    private String title;

    private Integer readTimes;

    private String kinds;

    private String content;

    private String author;
}
