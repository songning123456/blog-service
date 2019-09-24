package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.constant.HttpStatus;
import com.simple.blog.dto.CommonDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author songning
 * @date 2019/9/24
 * description
 */
@RestController
public class LoginController {

    /**
     * 无需登陆
     *
     * @return
     */
    @GetMapping("/hello")
    public String main() {
        return "hello";
    }

    @RequestMapping("/loginInfo")
    @ControllerAspectAnnotation(description = "未登陆时的情况")
    public <T> CommonDTO<T> loginInfo(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        CommonDTO<T> commonDTO = new CommonDTO<>();
        commonDTO.setMessage("请先登录");
        commonDTO.setStatus(HttpStatus.HTTP_NOT_AUTHORITATIVE);
        httpServletResponse.setStatus(203);
        return commonDTO;
    }

    /**
     * /test1 和 /test2 需要登录后才能访问
     */
    @GetMapping("/test1")
    public String test1() {
        return "test1";
    }

    @GetMapping("/test2")
    public String test2() {
        return "test2";
    }

    /**
     * 需要admin权限
     *
     * @return
     */
    @GetMapping("/admin")
    public String needAdminRole() {
        return "needAdminRole";
    }

    /**
     * 需要users权限
     *
     * @return
     */
    @GetMapping("/users")
    public String needUsersRole() {
        return "users角色才能访问";
    }

    @RequestMapping("/logoutSuccess")
    @ControllerAspectAnnotation(description = "注销登录成功后跳转的路由")
    public <T> CommonDTO<T> logoutSuccess() {
        CommonDTO<T> commonDTO = new CommonDTO<>();
        commonDTO.setMessage("退出成功");
        return commonDTO;
    }
}
