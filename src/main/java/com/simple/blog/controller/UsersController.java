package com.simple.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songning
 * @date 2019/9/19
 * description
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @GetMapping("/login")
    public String login() {
        return "users login success";
    }
}
