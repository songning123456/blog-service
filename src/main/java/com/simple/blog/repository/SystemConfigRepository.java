package com.simple.blog.repository;

import com.simple.blog.entity.SystemConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author songning
 * @create 2019/8/14 8:21
 */
@Repository
public interface SystemConfigRepository extends JpaRepository<SystemConfig, String> {

    @Query(value = "select config_value from system_config where config_key = ?1", nativeQuery = true)
    String findConfigValueByConfigKeyNative(String configKey);
}
