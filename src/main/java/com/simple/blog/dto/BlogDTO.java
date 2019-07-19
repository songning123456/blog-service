package com.simple.blog.dto;

import lombok.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

/**
 * @author sn
 */
@NoArgsConstructor
@Data
public class BlogDTO {
    private String id;

    private String title;

    private String summary;

    private Integer readTimes;

    private String kinds;

    private String content;

    private Date createTime;

    private Date updateTime;

    public BlogDTO(String id, String summary, String kinds, Integer readTimes, String title) {
        this.id = id;
        this.summary = summary;
        this.kinds = kinds;
        this.readTimes = readTimes;
        this.title = title;
    }
}
