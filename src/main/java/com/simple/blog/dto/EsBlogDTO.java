package com.simple.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author sn
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EsBlogDTO {

    private String id;

    private String title;

    private String summary;

    private Integer readTimes;

    private String kinds;

    private String content;

    private String author;

    private Date updateTime;

    private List<String> searchResult;
}
