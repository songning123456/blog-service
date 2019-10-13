package com.simple.blog.service.impl;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelDTO;
import com.simple.blog.entity.LabelConfig;
import com.simple.blog.repository.LabelConfigRepository;
import com.simple.blog.repository.LabelRelationRepository;
import com.simple.blog.service.LabelService;
import com.simple.blog.service.RedisService;
import com.simple.blog.util.ClassConvertUtil;
import com.simple.blog.util.DataBaseUtil;
import com.simple.blog.util.JsonUtil;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

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
    @Autowired
    private LabelConfigRepository labelConfigRepository;
    @Autowired
    private DataBaseUtil dataBaseUtil;

    private final Object object = new Object();

    @Override
    public CommonDTO<LabelDTO> getSelectedLabel() {
        CommonDTO<LabelDTO> commonDTO = new CommonDTO<>();
        String username = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.LOGIN_INFO + "username");
        Map<String, String> labelRelationMap = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.PERSON_ATTENTION_LABEL, username);
        List<String> labelNames;
        if (labelRelationMap.isEmpty()) {
            labelNames = labelRelationRepository.findLabelNameByUsernameAndSelectedNative(username, 1);
            redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.PERSON_ATTENTION_LABEL + username, JsonUtil.convertObject2String(labelNames));
        } else {
            String labelRelations = labelRelationMap.get(CommonConstant.REDIS_CACHE + CommonConstant.PERSON_ATTENTION_LABEL + username);
            labelNames = JsonUtil.convertString2Object(labelRelations, List.class);
        }
        List<LabelDTO> list = new ArrayList<>();
        labelNames.forEach(labelName -> {
            LabelDTO labelRelationDTO = LabelDTO.builder().labelName(labelName).build();
            list.add(labelRelationDTO);
        });
        commonDTO.setData(list);
        commonDTO.setTotal((long) list.size());
        return commonDTO;
    }

    @Override
    public CommonDTO<LabelDTO> getAllLabel(CommonVO<LabelVO> vo) {
        CommonDTO<LabelDTO> commonDTO = new CommonDTO<>();
        String labelName = vo.getCondition().getLabelName();
        List<LabelDTO> list = new ArrayList<>();
        LabelDTO labelDTO = null;
        // 根据 labelNam 模糊查询相关结果
        List<LabelConfig> configList = labelConfigRepository.findAllByLabelNameLikeNative(labelName);
        // 获取 此用户名下的关注标签
        String username = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.LOGIN_INFO + "username");
        String attentionLabel = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.PERSON_ATTENTION_LABEL + username);
        List attentionList = JsonUtil.convertString2Object(attentionLabel, List.class);
        // 获取所有标签
        Map<String, String> allLabel = redisService.getValues(CommonConstant.REDIS_CACHE + CommonConstant.ALL_LABEL);
        for (LabelConfig labelConfig : configList) {
            labelDTO = JsonUtil.convertString2Object(allLabel.get(CommonConstant.REDIS_CACHE + CommonConstant.ALL_LABEL + labelConfig.getLabelName()), LabelDTO.class);
            if (attentionList.contains(labelConfig.getLabelName())) {
                labelDTO.setIsAttention(1);
            } else {
                labelDTO.setIsAttention(0);
            }
            list.add(labelDTO);
        }
        commonDTO.setData(list);
        commonDTO.setTotal((long) list.size());
        return commonDTO;
    }

    @Override
    public CommonDTO<LabelDTO> statisticLabel(CommonVO<LabelVO> vo) {
        CommonDTO<LabelDTO> commonDTO = new CommonDTO<>();
        String labelName = vo.getCondition().getLabelName();
        String username = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.LOGIN_INFO + "username");
        // 统计关注数
        Map<String, Object> countMap = labelRelationRepository.countAttentionNative(labelName);
        Long attentionTotal = ((BigDecimal) countMap.get("total")).longValue();
        // 统计文章总数
        Long articleTotal = dataBaseUtil.getDataBase().statisticLabel(vo);
        Map<String, Object> attentionMap = labelRelationRepository.findAttentionByUsernameAndLabelNameNative(username, labelName);
        Integer isAttention = (int) attentionMap.get("attention");
        LabelDTO labelDTO = LabelDTO.builder().numOfArticle(articleTotal).isAttention(isAttention).numOfAttention(attentionTotal).build();
        commonDTO.setData(Collections.singletonList(labelDTO));
        commonDTO.setTotal(1L);
        return commonDTO;
    }

    @Override
    public CommonDTO<LabelDTO> updateAttention(CommonVO<LabelVO> commonVO) {
        CommonDTO<LabelDTO> commonDTO = new CommonDTO<>();
        String labelName = commonVO.getCondition().getLabelName();
        Integer attention = commonVO.getCondition().getAttention();
        String username = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.LOGIN_INFO + "username");
        synchronized (object) {
            // 更新关注状态
            Integer isSuccess = labelRelationRepository.updateByUsernameAndLabelNameAndAttentionNative(username, labelName, attention);
            if (isSuccess == 1) {
                // 修改redis缓存中关注的标签
                List<String> labelNames = labelRelationRepository.findLabelNameByUsernameAndSelectedNative(username, 1);
                redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.PERSON_ATTENTION_LABEL + username, JsonUtil.convertObject2String(labelNames));
            }
        }
        // 统计关注数
        Map<String, Object> countMap = labelRelationRepository.countAttentionNative(labelName);
        Long attentionTotal = ((BigDecimal) countMap.get("total")).longValue();
        // 是否已关注
        Map<String, Object> attentionMap = labelRelationRepository.findAttentionByUsernameAndLabelNameNative(username, labelName);
        Integer isAttention = (int) attentionMap.get("attention");
        LabelDTO labelDTO = LabelDTO.builder().numOfAttention(attentionTotal).isAttention(isAttention).build();
        commonDTO.setData(Collections.singletonList(labelDTO));
        commonDTO.setTotal(1L);
        return commonDTO;
    }

}
