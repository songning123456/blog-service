package com.simple.blog.repository;

import com.simple.blog.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

/**
 * @author songning
 * @date 2019/9/24
 * description
 */
public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * 根据用户名获取所有相关记录(按理有且只有一条)
     *
     * @param username
     * @return
     */
    @Query(value = "select username from users where username = :username", nativeQuery = true)
    String findUsernameByNameNative(@Param("username") String username);

    /**
     * 根据用户名查询密码
     * @param username
     * @return
     */
    @Query(value = "select password,role from users where username = :username", nativeQuery = true)
    Map<String, Object> findPasswordAndRoleByNameNative(@Param("username") String username);
}
