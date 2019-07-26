package com.simple.blog.repository;

import com.simple.blog.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author sn
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, String> {

    /**
     * 查询摘要等信息
     *
     * @param pageable
     * @return
     */
    @Query(value = "select id, title,summary, read_times, kinds, author, create_time, update_time " +
            "from blog where kinds like CONCAT('%', :kinds, '%')",
            countQuery = "select count(*) from blog where kinds like CONCAT('%', :kinds, '%')", nativeQuery = true)
    Page<Object[]> findAbstract(@Param("kinds") String kinds, Pageable pageable);


    /**
     * 查询文章详情
     *
     * @param id
     * @return
     */
    @Query(value = "select author, content, update_time as updateTime from blog where id= :id", nativeQuery = true)
    Map<String, Object> findByIdNative(@Param("id") String id);

    /**
     * 主要用于测试输出内容到文件和Map<String ,Object>
     *
     * @return
     */
    @Query(value = "select author, title from blog", nativeQuery = true)
    List<Map<String, Object>> getAllAuthorAndTitle();
}
