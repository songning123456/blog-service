package com.simple.blog.service.impl;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelRelationDTO;
import com.simple.blog.repository.LabelRelationRepository;
import com.simple.blog.service.LabelService;
import com.simple.blog.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author songning
 * @create 2019/7/31 18:01
 */
@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private RedisService redisService;
    @Autowired
    private LabelRelationRepository labelRelationRepository;

    @Override
    public CommonDTO<LabelRelationDTO> getSelectedLabel() {
        CommonDTO<LabelRelationDTO> commonDTO = new CommonDTO<>();
        String username = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.LOGIN_INFO + "username");
        List<String> labelNames = labelRelationRepository.findLabelNameByUsernameAndSelectedNative(username);
        List<LabelRelationDTO> list = new ArrayList<>();
        labelNames.forEach(labelName -> {
            LabelRelationDTO labelRelationDTO = LabelRelationDTO.builder().labelName(labelName).build();
            list.add(labelRelationDTO);
        });
        commonDTO.setData(list);
        commonDTO.setTotal((long) list.size());
        return commonDTO;
    }

}
