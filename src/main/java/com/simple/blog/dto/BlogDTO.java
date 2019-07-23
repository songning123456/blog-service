package com.simple.blog.dto;

import lombok.*;

import java.sql.Timestamp;

/**
 * @author sn
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BlogDTO {
    private String id;

    private String title;

    private String summary;

    private Integer readTimes;

    private String kinds;

    private String content;

    private String author;

    private java.sql.Timestamp createTime;

    private java.sql.Timestamp updateTime;

    public BlogDTO(String id, String title, String summary, Integer readTimes, String kinds, String author, Timestamp createTime, Timestamp updateTime) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.readTimes = readTimes;
        this.kinds = kinds;
        this.author = author;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public BlogDTO(String author, String content, Timestamp updateTime) {
        this.author = author;
        this.content = content;
        this.updateTime = updateTime;
    }
}
