package com.simple.blog.repository;

import com.simple.blog.entity.Blogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author sn
 */
@Repository
public interface BloggerRepository extends JpaRepository<Blogger, String> {

    /**
     * @param userName
     * @return
     */
    @Query(value = "select * from blogger where user_name = ?1", nativeQuery = true)
    List<Blogger> findByUserNameNative(String userName);
}
