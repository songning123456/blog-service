package com.simple.blog.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by songning on 2019/8/25 2:03 PM
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "PersonalInformation")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PersonalInformation {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    @Column(name = "infoOwner", columnDefinition = "VARCHAR(50) NOT NULL COMMENT '信息所属人'")
    private String infoOwner;

    @Column(name="infoType", columnDefinition = "VARCHAR(50) NOT NULL COMMENT '信息类型'")
    private String infoType;

    @Column(name = "mechanism", columnDefinition = "VARCHAR(100) NOT NULL COMMENT '机构类型'")
    private String mechanism;

    @Column(name = "position", columnDefinition = "VARCHAR(100) NOT NULL COMMENT '在职职位'")
    private String position;

    @Column(name = "introduction", columnDefinition = "TEXT NOT NULL COMMENT '介绍'")
    private String introduction;

    @Column(name = "startTime", columnDefinition = "DATETIME NOT NULL COMMENT '起始时间'")
    private Date startTime;

    @Column(name = "endTime", columnDefinition = "DATETIME NOT NULL COMMENT '结束时间'")
    private Date endTime;

}