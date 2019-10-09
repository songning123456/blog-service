package com.simple.blog.repository;

import com.simple.blog.entity.LabelRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author songning
 * @create 2019/8/6 8:25
 */
@Repository
public interface LabelRelationRepository extends JpaRepository<LabelRelation, String> {

    @Query(value = "select label_name from label_relation where username = ?1 and attention = ?2 order by label_name asc", nativeQuery = true)
    List<String> findLabelNameByUsernameAndSelectedNative(String username, Integer attention);
}
