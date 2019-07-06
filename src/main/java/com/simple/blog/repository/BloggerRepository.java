package com.simple.blog.repository;

import com.simple.blog.entity.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sn
 */
@Repository
public interface BloggerRepository extends JpaRepository<Blogger, Long> {
}
