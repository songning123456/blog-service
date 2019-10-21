package com.simple.blog.util;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author songning
 * @date 2019/10/21
 * description
 */
@Component
public class HttpServletRequestUtil {

    @Autowired
    private RedisService redisService;

    public String getUsername() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String token = request.getHeader("Authorization");
            String username = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.LOGIN_INFO + token);
            return username;
        } else {
            return "none";
        }
    }
}
