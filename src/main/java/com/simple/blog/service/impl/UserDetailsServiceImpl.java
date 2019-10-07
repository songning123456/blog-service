package com.simple.blog.service.impl;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.repository.UsersRepository;
import com.simple.blog.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author songning
 * @date 2019/9/18
 * description
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RedisService redisService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String user = usersRepository.findUsernameByNameNative(username);
        if (username.equals(user)) {
            Map<String, Object> map = usersRepository.findPasswordAndRoleByNameNative(username);
            String password = (String) map.get("password");
            String role = (String) map.get("role");
            if (CommonConstant.LOGIN_USER.equals(role)) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
                grantedAuthorities.add(grantedAuthority);
                // 把 用户名 存储redis缓存 以便后续操作(SystemConfig, LabelConfig)
                redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.LOGIN_INFO + "username", username);
                return new User(username, new BCryptPasswordEncoder().encode(password), grantedAuthorities);
            } else if (CommonConstant.LOGIN_ADMIN.equals(role)) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
                grantedAuthorities.add(grantedAuthority);
                redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.LOGIN_INFO + "username", username);
                return new User(username, new BCryptPasswordEncoder().encode(password), grantedAuthorities);
            }
        }
        return null;
    }
}
