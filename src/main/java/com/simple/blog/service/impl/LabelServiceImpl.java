package com.simple.blog.service.impl;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelConfigDTO;
import com.simple.blog.dto.LabelRelationDTO;
import com.simple.blog.dto.LabelStatisticDTO;
import com.simple.blog.entity.LabelConfig;
import com.simple.blog.entity.LabelRelation;
import com.simple.blog.repository.LabelConfigRepository;
import com.simple.blog.repository.LabelRelationRepository;
import com.simple.blog.service.LabelService;
import com.simple.blog.service.RedisService;
import com.simple.blog.util.ClassConvertUtil;
import com.simple.blog.util.DataBaseUtil;
import com.simple.blog.util.JsonUtil;
import com.simple.blog.util.MapConvertEntityUtil;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelStatisticVO;
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
    public CommonDTO<LabelRelationDTO> getSelectedLabel() {
        CommonDTO<LabelRelationDTO> commonDTO = new CommonDTO<>();
        String username = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.LOGIN_INFO + "username");
        Map<String, String> labelRelationMap = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_RELATION, username);
        List<String> labelNames;
        if (labelRelationMap.isEmpty()) {
            labelNames = labelRelationRepository.findLabelNameByUsernameAndSelectedNative(username, 1);
            redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.LABEL_RELATION + username, JsonUtil.convertObject2String(labelNames));
        } else {
            String labelRelations = labelRelationMap.get(CommonConstant.REDIS_CACHE + CommonConstant.LABEL_RELATION + username);
            labelNames = JsonUtil.convertString2Object(labelRelations, List.class);
        }
        List<LabelRelationDTO> list = new ArrayList<>();
        labelNames.forEach(labelName -> {
            LabelRelationDTO labelRelationDTO = LabelRelationDTO.builder().labelName(labelName).build();
            list.add(labelRelationDTO);
        });
        commonDTO.setData(list);
        commonDTO.setTotal((long) list.size());
        return commonDTO;
    }

    @Override
    public CommonDTO<LabelConfigDTO> getAllLabel() {
        CommonDTO<LabelConfigDTO> commonDTO = new CommonDTO<>();
        List<LabelConfigDTO> list = new ArrayList<>();
        Map<String, String> labelConfigs = redisService.getValues(CommonConstant.REDIS_CACHE, CommonConstant.LABEL_CONFIG);
        if (labelConfigs.isEmpty()) {
            List<LabelConfig> configList = labelConfigRepository.findAll();
            ClassConvertUtil.populateList(configList, list, LabelConfigDTO.class);
            configList.forEach(item -> redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.LABEL_CONFIG + item.getLabelName(), JsonUtil.convertObject2String(item)));
        } else {
            labelConfigs.forEach((key, value) -> {
                LabelConfig labelConfig = JsonUtil.convertString2Object(value, LabelConfig.class);
                LabelConfigDTO labelConfigDTO = LabelConfigDTO.builder().labelName(labelConfig.getLabelName()).labelPhoto(labelConfig.getLabelPhoto()).build();
                list.add(labelConfigDTO);
            });
        }
        commonDTO.setData(list);
        commonDTO.setTotal((long) list.size());
        return commonDTO;
    }

    @Override
    public CommonDTO<LabelStatisticDTO> statisticLabel(CommonVO<LabelStatisticVO> vo) {
        CommonDTO<LabelStatisticDTO> commonDTO = new CommonDTO<>();
        String labelName = vo.getCondition().getLabelName();
        String username = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.LOGIN_INFO + "username");
        // 统计关注数
        Map<String, Object> countMap = labelRelationRepository.countAttentionNative(labelName);
        Long attentionTotal = ((BigDecimal) countMap.get("total")).longValue();
        // 统计文章总数
        Long articleTotal = dataBaseUtil.getDataBase().statisticLabel(vo);
        Map<String, Object> attentionMap = labelRelationRepository.findAttentionByUsernameAndLabelNameNative(username, labelName);
        Integer isAttention = (int) attentionMap.get("attention");
        LabelStatisticDTO labelStatisticDTO = LabelStatisticDTO.builder().articleTotal(articleTotal).isAttention(isAttention).attentionTotal(attentionTotal).build();
        commonDTO.setData(Collections.singletonList(labelStatisticDTO));
        commonDTO.setTotal(1L);
        return commonDTO;
    }

    @Override
    public CommonDTO<LabelStatisticDTO> updateAttention(CommonVO<LabelStatisticVO> commonVO) {
        CommonDTO<LabelStatisticDTO> commonDTO = new CommonDTO<>();
        String labelName = commonVO.getCondition().getLabelName();
        Integer attention = commonVO.getCondition().getAttention();
        String username = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.LOGIN_INFO + "username");
        synchronized (object) {
            // 更新关注状态
            Integer isSuccess = labelRelationRepository.updateByUsernameAndLabelNameAndAttentionNative(username, labelName, attention);
            if (isSuccess == 1) {
                // 修改redis缓存中关注的标签
                List<String> labelNames = labelRelationRepository.findLabelNameByUsernameAndSelectedNative(username, 1);
                redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.LABEL_RELATION + username, JsonUtil.convertObject2String(labelNames));
            }
        }
        // 统计关注数
        Map<String, Object> countMap = labelRelationRepository.countAttentionNative(labelName);
        Long attentionTotal = ((BigDecimal) countMap.get("total")).longValue();
        // 是否已关注
        Map<String, Object> attentionMap = labelRelationRepository.findAttentionByUsernameAndLabelNameNative(username, labelName);
        Integer isAttention = (int) attentionMap.get("attention");
        LabelStatisticDTO labelStatisticDTO = LabelStatisticDTO.builder().attentionTotal(attentionTotal).isAttention(isAttention).build();
        commonDTO.setData(Collections.singletonList(labelStatisticDTO));
        commonDTO.setTotal(1L);
        return commonDTO;
    }

}
