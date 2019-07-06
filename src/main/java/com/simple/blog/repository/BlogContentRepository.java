package com.simple.blog.repository;

import com.simple.blog.entity.BlogContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sn
 */
@Repository
public interface BlogContentRepository extends JpaRepository<BlogContent, Long> {
}
