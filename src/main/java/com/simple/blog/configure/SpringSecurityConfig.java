package com.simple.blog.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author songning
 * @date 2019/9/18
 * description
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        super.configure(webSecurity);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        // passwordEncoder是对密码的加密处理，如果user中密码没有加密，则可以不加此方法。注意加密请使用security自带的加密方式
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 禁用了 csrf 功能
        httpSecurity.csrf().disable()
                // 限定签名成功的请求
                .authorizeRequests()
                // 对decision和govern下的接口需要USER或者ADMIN权限
                .antMatchers("/decision/**", "/govern/**", "/users/*").hasAnyRole("USERS", "ADMIN")
                // users/login不限定
                .antMatchers("/users/login").permitAll()
                // 对admin下的接口需要ADMIN权限
                .antMatchers("/admin/**").hasRole("ADMIN")
                // 不拦截oauth开放的资源
                .antMatchers("/oauth/**").permitAll()
                // 其他没有限定的请求，允许访问
                .anyRequest().permitAll()
                // 对于没有配置权限的其他请求允许匿名访问
                .and().anonymous()
                // 使用spring security默认登录页面
                .and().formLogin()
                // 启用http基础验证
                .and().httpBasic();
    }
}
