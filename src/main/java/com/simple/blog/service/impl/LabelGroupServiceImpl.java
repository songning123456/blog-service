package com.simple.blog.service.impl;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelGroupDTO;
import com.simple.blog.entity.LabelGroup;
import com.simple.blog.repository.LabelGroupRepository;
import com.simple.blog.service.LabelGroupService;
import com.simple.blog.service.RedisService;
import com.simple.blog.util.ClassConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author songning
 * @create 2019/7/31 18:01
 */
@Service
public class LabelGroupServiceImpl implements LabelGroupService {
    @Autowired
    private LabelGroupRepository labelGroupRepository;
    @Autowired
    private RedisService redisService;

    @Override
    public CommonDTO<LabelGroupDTO> getLabelCache() {
        CommonDTO<LabelGroupDTO> commonDTO = new CommonDTO<>();
        List<LabelGroupDTO> list = new ArrayList<>();
        Map<String, Object> map = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_GROUP);
        if (map.isEmpty()) {
            List<LabelGroup> labelGroups = labelGroupRepository.findAll();
            labelGroups.forEach(item -> redisService.setValue(item.getLabelGroupName(), item, CommonConstant.REDIS_CACHE, CommonConstant.LABEL_GROUP));
            ClassConvertUtil.populateList(labelGroups, list, LabelGroupDTO.class);
        } else {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                LabelGroup labelGroup = (LabelGroup) entry.getValue();
                LabelGroupDTO labelGroupDTO = LabelGroupDTO.builder().id(labelGroup.getId()).labelGroupName(labelGroup.getLabelGroupName()).description(labelGroup.getDescription()).build();
                list.add(labelGroupDTO);
            }
        }
        commonDTO.setData(list);
        return commonDTO;
    }
}
