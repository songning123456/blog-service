package com.simple.blog.repository;

import com.simple.blog.entity.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author songning
 * @create 2019/8/14 8:21
 */
@Repository
public interface SystemConfigRepository extends JpaRepository<SystemConfig, String> {
}
