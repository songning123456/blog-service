package com.simple.blog.service.impl;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelDTO;
import com.simple.blog.dto.RegisterDTO;
import com.simple.blog.entity.Blogger;
import com.simple.blog.entity.LabelRelation;
import com.simple.blog.entity.SystemConfig;
import com.simple.blog.entity.Users;
import com.simple.blog.repository.BloggerRepository;
import com.simple.blog.repository.LabelRelationRepository;
import com.simple.blog.repository.SystemConfigRepository;
import com.simple.blog.repository.UsersRepository;
import com.simple.blog.service.MemoryService;
import com.simple.blog.service.RegisterService;
import com.simple.blog.util.ClassConvertUtil;
import com.simple.blog.util.JsonUtil;
import com.simple.blog.util.MapConvertEntityUtil;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelVO;
import com.simple.blog.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author songning
 * @date 2019/11/1
 * description
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private BloggerRepository bloggerRepository;
    @Autowired
    private SystemConfigRepository systemConfigRepository;
    @Autowired
    private LabelRelationRepository labelRelationRepository;
    @Autowired
    private MemoryService memoryService;

    private final Object object = new Object();

    @Override
    public CommonDTO<RegisterDTO> registerAll(CommonVO<RegisterVO> commonVO) {
        CommonDTO<RegisterDTO> commonDTO = new CommonDTO<>();
        RegisterVO registerVO = commonVO.getCondition();
        String username = commonVO.getCondition().getUsername();
        try {
            // 注册用户表
            Users users = new Users();
            ClassConvertUtil.populate(registerVO, users);
            users.setRole(CommonConstant.LOGIN_USER);
            usersRepository.save(users);
            String userId = usersRepository.findUserIdByNameNative(username);
            try {
                // 注册 blogger
                Blogger blogger = new Blogger();
                ClassConvertUtil.populate(registerVO, blogger);
                blogger.setUpdateTime(new Date());
                blogger.setUserId(userId);
                bloggerRepository.save(blogger);
                try {
                    // 注册 system-config
                    List<Map<String, Object>> mapList = systemConfigRepository.findDistinctNative();
                    for (Map<String, Object> map : mapList) {
                        SystemConfig systemConfig = (SystemConfig) MapConvertEntityUtil.mapToEntity(SystemConfig.class, map);
                        systemConfig.setUsername(username);
                        systemConfigRepository.save(systemConfig);
                    }
                    try {
                        // 注册label-relation
                        List<LabelVO> src = commonVO.getCondition().getLabelVOS();
                        for (LabelVO labelVO : src) {
                            String labelName = labelVO.getLabelName();
                            Integer attention = labelVO.getAttention();
                            LabelRelation labelRelation = LabelRelation.builder().labelName(labelName).username(username).attention(attention).build();
                            synchronized (object) {
                                labelRelationRepository.save(labelRelation);
                                if (attention == 1) {
                                    Object result = memoryService.getValue(CommonConstant.MEMORY_CACHE + CommonConstant.ALL_LABEL + labelName);
                                    LabelDTO labelDTO = (LabelDTO) result;
                                    labelDTO.setNumOfAttention(labelDTO.getNumOfAttention() + 1);
                                    memoryService.setValue(CommonConstant.MEMORY_CACHE + CommonConstant.ALL_LABEL + labelName, labelDTO);
                                }
                            }
                        }
                        // 注册成功时，刷新SystemConfig缓存
                        List<SystemConfig> systemConfigList = systemConfigRepository.findAll();
                        memoryService.deleteValues(CommonConstant.MEMORY_CACHE, CommonConstant.SYSTEM_CONFIG);
                        systemConfigList.forEach(item -> memoryService.setValue(CommonConstant.MEMORY_CACHE + CommonConstant.SYSTEM_CONFIG + item.getUsername() + ":" + item.getConfigKey(), item));
                    } catch (Exception e) {
                        synchronized (object) {
                            labelRelationRepository.deleteAllByUsername(username);
                            systemConfigRepository.deleteAllByUsername(username);
                            usersRepository.deleteAllByUsername(username);
                            bloggerRepository.deleteAllByUsername(username);
                        }
                        commonDTO.setStatus(500);
                        commonDTO.setMessage("注册label-relation失败");
                    }
                } catch (Exception e) {
                    synchronized (object) {
                        systemConfigRepository.deleteAllByUsername(username);
                        usersRepository.deleteAllByUsername(username);
                        bloggerRepository.deleteAllByUsername(username);
                    }
                    commonDTO.setStatus(500);
                    commonDTO.setMessage("注册system-config失败");
                }
            } catch (Exception e) {
                usersRepository.deleteAllByUsername(username);
                commonDTO.setStatus(500);
                commonDTO.setMessage("注册blogger失败");
            }
        } catch (Exception e) {
            synchronized (object) {
                labelRelationRepository.deleteAllByUsername(username);
                systemConfigRepository.deleteAllByUsername(username);
                usersRepository.deleteAllByUsername(username);
                bloggerRepository.deleteAllByUsername(username);
            }
            commonDTO.setStatus(500);
            commonDTO.setMessage("注册users失败");
        }
        return commonDTO;
    }
}
