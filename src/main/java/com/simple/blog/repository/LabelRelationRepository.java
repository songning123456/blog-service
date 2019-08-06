package com.simple.blog.repository;

import com.simple.blog.entity.LabelRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author songning
 * @create 2019/8/6 8:25
 */
@Repository
public interface LabelRelationRepository extends JpaRepository<LabelRelation, String> {
}
