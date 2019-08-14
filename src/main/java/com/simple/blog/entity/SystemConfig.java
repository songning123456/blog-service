package com.simple.blog.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @Author songning
 * @create 2019/8/14 8:13
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "SystemConfig")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class SystemConfig {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;

    @Column(name = "configKey", columnDefinition = "VARCHAR(255) NOT NULL")
    private String configKey;

    @Column(name = "configValue", columnDefinition = "VARCHAR(255) NOT NULL")
    private String configValue;

    @Column(name = "valueDescription", columnDefinition = "VARCHAR(255)")
    private String valueDescription;
}
