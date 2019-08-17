package com.simple.blog.vo;

import lombok.Data;

/**
 * @author sn
 */
@Data
public class EsBlogVO {
    private String id;

    private String title;

    private String summary;

    private Integer readTimes;

    private String kinds;

    private String content;

    private String author;
}
