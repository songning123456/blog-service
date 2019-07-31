package com.simple.blog.repository;

import com.simple.blog.entity.LabelGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author songning
 * @create 2019/7/31 8:23
 */
@Repository
public interface LabelGroupRepository extends JpaRepository<LabelGroup, String> {
}
