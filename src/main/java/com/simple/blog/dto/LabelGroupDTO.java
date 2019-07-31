package com.simple.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author songning
 * @create 2019/7/31 17:58
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LabelGroupDTO {
    private String id;

    private String labelGroupName;

    private String description;
}
