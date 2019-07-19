package com.simple.blog.dto;

import lombok.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.Date;

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
}
