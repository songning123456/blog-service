package com.simple.blog.service.impl;

import com.simple.blog.entity.Admin;
import com.simple.blog.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songning
 * @date 2019/9/18
 * description
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if ("users".equals(username)) {
            Users users = new Users();
            users.setUsername("users");
            users.setPassword("123456");
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USERS");
            grantedAuthorities.add(grantedAuthority);
            return new User(users.getUsername(), new BCryptPasswordEncoder().encode(users.getPassword()), grantedAuthorities);
        }
        if ("admin".equals(username)) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword("123456");
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
            grantedAuthorities.add(grantedAuthority);
            return new User(admin.getUsername(), new BCryptPasswordEncoder().encode(admin.getPassword()), grantedAuthorities);
        }
        return null;
    }
}
