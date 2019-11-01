package com.simple.blog.repository;

import com.simple.blog.entity.SystemConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Query(value = "select * from system_config where username = ?1 and config_key like %?2% and config_value like %?3% and value_description like %?4%", nativeQuery = true)
    Page<SystemConfig> findByUsernameAndConfigKeyAndConfigValueAndValueDescriptionNative(String username, String configKey, String configValue, String valueDescription, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update system_config set config_key = ?2, config_value = ?3, value_description = ?4 where username = ?1", nativeQuery = true)
    void updateSystemConfig(String username, String configKey, String configValue, String valueDescription);

    @Query(value = "select distinct (config_key) as configKey,config_value as configValue, value_description as valueDescription from system_config", nativeQuery = true)
    List<Map<String, Object>> findDistinctNative();

    void deleteAllByUsername(String username);
}
