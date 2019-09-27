package com.simple.blog.service.impl;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelGroupDTO;
import com.simple.blog.dto.LabelRelationDTO;
import com.simple.blog.entity.LabelGroup;
import com.simple.blog.entity.LabelRelation;
import com.simple.blog.repository.LabelGroupRepository;
import com.simple.blog.repository.LabelRelationRepository;
import com.simple.blog.service.LabelService;
import com.simple.blog.service.RedisService;
import com.simple.blog.util.ClassConvertUtil;
import com.simple.blog.util.JsonUtil;
import com.simple.blog.util.StringUtil;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelGroupVO;
import com.simple.blog.vo.LabelRelationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author songning
 * @create 2019/7/31 18:01
 */
@Service
public class LabelServiceImpl implements LabelService {
    @Autowired
    private LabelGroupRepository labelGroupRepository;
    @Autowired
    private LabelRelationRepository labelRelationRepository;
    @Autowired
    private RedisService redisService;
    private final Object object = new Object();

    @Override
    public CommonDTO<LabelGroupDTO> getLabelCache() {
        CommonDTO<LabelGroupDTO> commonDTO = new CommonDTO<>();
        List<LabelGroupDTO> list = new ArrayList<>();
        Map<String, String> map = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_GROUP);
        if (map.isEmpty()) {
            List<LabelGroup> labelGroups = labelGroupRepository.findAll();
            labelGroups.forEach(item -> redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.LABEL_GROUP + item.getLabelGroupName(), JsonUtil.convertObject2String(item)));
            ClassConvertUtil.populateList(labelGroups, list, LabelGroupDTO.class);
        } else {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                LabelGroup labelGroup = JsonUtil.convertString2Object(entry.getValue(), LabelGroup.class);
                LabelGroupDTO labelGroupDTO = LabelGroupDTO.builder().id(labelGroup.getId()).labelGroupName(labelGroup.getLabelGroupName()).description(labelGroup.getDescription()).build();
                list.add(labelGroupDTO);
            }
        }
        commonDTO.setData(list);
        commonDTO.setTotal((long) list.size());
        return commonDTO;
    }

    @Override
    public CommonDTO<LabelGroupDTO> saveLabelGroup(CommonVO<List<LabelGroupVO>> commonVO) {
        List<LabelGroupVO> src = commonVO.getCondition();
        List<LabelGroup> target = new ArrayList<>();
        ClassConvertUtil.populateList(src, target, LabelGroup.class);
        labelGroupRepository.saveAll(target);
        return new CommonDTO<>();
    }

    @Override
    public CommonDTO<LabelRelationDTO> saveLabelRelation(CommonVO<List<LabelRelationVO>> commonVO) {
        List<LabelRelationVO> src = commonVO.getCondition();
        List<LabelRelation> target = new ArrayList<>();
        ClassConvertUtil.populateList(src, target, LabelRelation.class);
        labelRelationRepository.saveAll(target);
        return new CommonDTO<>();
    }

    @Override
    public Map<String, Object> getGroupCache() {
        Map<String, Object> result = new HashMap<>(10);
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, String> relationMap = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_RELATION);
        Map<String, String> groupMap = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_GROUP);
        if (relationMap.isEmpty()) {
            synchronized (this) {
                List<LabelRelation> list = labelRelationRepository.findAll();
                list.forEach(item -> redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.LABEL_RELATION + item.getLabelGroupName() + "-" + item.getLabelName(), JsonUtil.convertObject2String(item)));
                relationMap = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_RELATION);
            }
        }
        if (groupMap.isEmpty()) {
            synchronized (this) {
                List<LabelGroup> list = labelGroupRepository.findAll();
                list.forEach(item -> redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.LABEL_GROUP + item.getLabelGroupName(), JsonUtil.convertObject2String(item)));
                groupMap = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_GROUP);
            }
        }
        Set<String> groupNameSet = groupMap.keySet();
        Set<String> relationSet = relationMap.keySet();
        groupNameSet.forEach(groupName -> {
            List options = new ArrayList();
            Map<String, Object> groups = new HashMap<>(10);
            LabelGroup labelGroup = JsonUtil.convertString2Object(redisService.getValue(groupName), LabelGroup.class);
            groups.put("label", labelGroup.getDescription());
            relationSet.forEach(relation -> {
                String[] relations = StringUtil.splitString(relation, ":", 2).split("-");
                if (relations[0].equals(labelGroup.getLabelGroupName())) {
                    Map<String, String> labelVale = new HashMap<>(2);
                    labelVale.put("label", relations[1]);
                    labelVale.put("value", relations[1]);
                    options.add(labelVale);
                }
            });
            groups.put("options", options);
            data.add(groups);
        });
        result.put("data", data);
        return result;
    }

    @Override
    public CommonDTO<LabelRelationDTO> getAllLabelName() {
        CommonDTO<LabelRelationDTO> commonDTO = new CommonDTO<>();
        List<LabelRelationDTO> list = new ArrayList<>();
        Map<String, String> relationMap = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_RELATION);
        if (relationMap.isEmpty()) {
            synchronized (object) {
                List<LabelRelation> labelRelations = labelRelationRepository.findAll();
                labelRelations.forEach(item -> redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.LABEL_RELATION + item.getLabelGroupName() + "-" + item.getLabelName(), JsonUtil.convertObject2String(item)));
                relationMap = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_RELATION);
            }
        }
        relationMap.keySet().forEach(item -> {
            String labelName = StringUtil.splitString(item, ":", 2).split("-")[1];
            list.add(LabelRelationDTO.builder().labelName(labelName).build());
        });
        commonDTO.setData(list);
        commonDTO.setTotal((long) list.size());
        return commonDTO;
    }
}
