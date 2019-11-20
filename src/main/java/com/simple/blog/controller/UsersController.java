package com.simple.blog.controller;

import com.simple.blog.annotation.ControllerAspectAnnotation;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.UsersDTO;
import com.simple.blog.service.UsersService;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author songning
 * @date 2019/10/24
 * description
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/exist")
    @ControllerAspectAnnotation(description = "获取标签")
    public CommonDTO<UsersDTO> judgeIsExist(@RequestBody CommonVO<UsersVO> commonVO) {
        CommonDTO<UsersDTO> commonDTO = usersService.isExist(commonVO);
        return commonDTO;
    }

    @PostMapping("/modify")
    @ControllerAspectAnnotation(description = "修改用户密码")
    public CommonDTO<UsersDTO> modifyPasswords(@RequestBody CommonVO<UsersVO> commonVO) {
        CommonDTO<UsersDTO> commonDTO = usersService.modifyPassword(commonVO);
        return commonDTO;
    }
}
