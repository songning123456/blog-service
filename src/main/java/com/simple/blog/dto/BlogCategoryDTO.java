package com.simple.blog.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author sn
 */
@Data
public class BlogCategoryDTO {
    private Long id;

    private String categoryName;

    private Long blogId;

    private Date createTime;

    private Date updateTime;
}
