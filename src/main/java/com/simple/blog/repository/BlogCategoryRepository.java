package com.simple.blog.repository;

import com.simple.blog.entity.BlogCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sn
 */
@Repository
public interface BlogCategoryRepository extends JpaRepository<BlogCategory, Long> {
}
