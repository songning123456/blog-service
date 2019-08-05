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
public class LabelConfig {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    @Column(name = "labelName", columnDefinition = "VARCHAR(255)  NOT NULL UNIQUE COMMENT '标签名'")
    private String labelName;
}