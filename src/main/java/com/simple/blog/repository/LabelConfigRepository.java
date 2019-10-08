package com.simple.blog.repository;

import com.simple.blog.entity.LabelConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author songning
 * @date 2019/10/8
 * description
 */
@Repository
public interface LabelConfigRepository extends JpaRepository<LabelConfig, String> {
}
