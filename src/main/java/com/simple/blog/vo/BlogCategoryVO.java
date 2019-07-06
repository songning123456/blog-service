package com.simple.blog.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author sn
 */
@Data
public class BlogCategoryVO {
    private Long id;

    private String categoryName;

    private Long blogId;

    private Date createTime;

    private Date updateTime;
}
