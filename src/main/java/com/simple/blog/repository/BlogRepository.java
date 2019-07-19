package com.simple.blog.repository;

import com.simple.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author sn
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    /**
     * 查询摘要等信息
     *
     * @param pageable
     * @return
     */
    @Query(value = "select id, summary, kinds,read_times, title from blog where kinds like CONCAT('%', :kinds, '%')", nativeQuery = true)
    Page<Object[]> findAbstract(@Param("kinds") String kinds, Pageable pageable);
}
