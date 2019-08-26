package com.simple.blog.dto;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
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
