package com.simple.blog.repository;

import com.simple.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sn
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
