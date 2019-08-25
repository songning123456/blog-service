package com.simple.blog.dto;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by songning on 2019/8/25 2:46 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonalInformationDTO {
    private String id;

    private String infoOwner;

    private String infoType;

    private String mechanism;

    private String position;

    private String introduction;

    private String startTime;

    private String endTime;
}
