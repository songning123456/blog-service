package com.simple.blog.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author songning
 * @create 2019/7/31 7:57
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "LabelGroup")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class LabelGroup {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    @Column(name = "labelGroupName", columnDefinition = "VARCHAR(255)  NOT NULL COMMENT '标签分组名'")
    private String labelGroupName;

    @Column(name = "description", columnDefinition = "VARCHAR(255) COMMENT '标签分组描述'")
    private String description;
}
