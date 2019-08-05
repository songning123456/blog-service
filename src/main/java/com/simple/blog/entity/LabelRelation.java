package com.simple.blog.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author songning
 * @create 2019/8/5 10:57
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@Entity
@Table(name = "LabelGroup")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class LabelRelation {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    @Column(name = "labelGroupName", columnDefinition = "VARCHAR(255)  NOT NULL UNIQUE COMMENT '标签分组名'")
    private String labelGroupName;

    @Column(name = "labelName", columnDefinition = "VARCHAR(255)  NOT NULL UNIQUE COMMENT '标签名'")
    private String labelName;
}
