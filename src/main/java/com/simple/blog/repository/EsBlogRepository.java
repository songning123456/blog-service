package com.simple.blog.repository;

import com.simple.blog.entity.EsBlog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author songning
 * @create 2019/8/13 8:05
 */
@Repository
public interface EsBlogRepository extends ElasticsearchRepository<EsBlog, String> {

}
