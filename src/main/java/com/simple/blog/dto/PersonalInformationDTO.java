package com.simple.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by songning on 2019/8/25 2:46 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonalInformationDTO {
    private String type;

    private List info;
}
