package com.simple.blog.repository;

import com.simple.blog.entity.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author sn
 */
@Repository
public interface BloggerRepository extends JpaRepository<Blogger, String> {

    /**
     * @param userId
     * @return
     */
    @Query(value = "select author, real_name as realName, age, email, gender, head_portrait as headPortrait, motto,profession,telephone from blogger where user_id = ?1", nativeQuery = true)
    List<Map<String, Object>> findByUserIdNative(String userId);

    @Query(value = "select author, real_name as realName, age, email, gender, head_portrait as headPortrait, motto,profession,telephone from blogger where username = ?1", nativeQuery = true)
    List<Map<String, Object>> findByUsernameNative(String username);

    @Transactional
    @Modifying
    void deleteAllByUsername(String username);

    @Query(value = "select distinct(user_id) as userId,author from blogger", nativeQuery = true)
    List<Map<String, Object>> findAllAuthorAndUserIdNative();
}
