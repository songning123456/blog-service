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

    /**
     * 根据用户名 和 配置项key 获取数据源
     *
     * @param username
     * @param configKey
     * @return
     */
    @Query(value = "select config_value from system_config where username = ?1 and config_key = ?2", nativeQuery = true)
    String findConfigValueByUsernameAndConfigKeyNative(String username, String configKey);
}
