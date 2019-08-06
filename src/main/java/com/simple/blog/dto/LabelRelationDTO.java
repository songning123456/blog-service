package com.simple.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author songning
 * @create 2019/8/6 8:21
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class LabelRelationDTO {
    private String labelGroupName;

    private String labelName;
}
