package com.simple.blog.service.impl;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.UsersDTO;
import com.simple.blog.entity.Users;
import com.simple.blog.repository.UsersRepository;
import com.simple.blog.service.UsersService;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.UsersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;

/**
 * @author songning
 * @date 2019/10/24
 * description
 */
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public CommonDTO<UsersDTO> isExist(CommonVO<UsersVO> commonVO) {
        CommonDTO<UsersDTO> commonDTO = new CommonDTO<>();
        String username = commonVO.getCondition().getUsername();
        String name = usersRepository.findUsernameByNameNative(username);
        UsersDTO usersDTO;
        if (StringUtils.isEmpty(name)) {
            usersDTO = UsersDTO.builder().isExist(false).build();
        } else {
            usersDTO = UsersDTO.builder().isExist(true).build();
        }
        commonDTO.setData(Collections.singletonList(usersDTO));
        commonDTO.setTotal(1L);
        return commonDTO;
    }

    @Override
    public CommonDTO<UsersDTO> saveUser(CommonVO<UsersVO> commonVO) {
        String username = commonVO.getCondition().getUsername();
        String password = commonVO.getCondition().getPassword();
        // 默认所有注册的用户权限都为 user
        String role = CommonConstant.LOGIN_USER;
        Users users = Users.builder().username(username).password(password).role(role).build();
        usersRepository.save(users);
        return new CommonDTO<>();
    }
}
