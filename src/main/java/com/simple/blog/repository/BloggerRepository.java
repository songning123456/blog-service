package com.simple.blog.repository;

import com.simple.blog.entity.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author sn
 */
@Repository
public interface BloggerRepository extends JpaRepository<Blogger, String> {

    /**
     * @param author
     * @return
     */
    @Query(value = "select author, real_name as realName, age, email, gender, head_portrait as headPortrait, introduction,profession,telephone from blogger where author = ?1", nativeQuery = true)
    List<Map<String, Object>> findByAuthorNative(String author);

    @Query(value = "select author, real_name as realName, age, email, gender, head_portrait as headPortrait, introduction,profession,telephone from blogger where username = ?1", nativeQuery = true)
    List<Map<String, Object>> findByUsernameNative(String username);
}
