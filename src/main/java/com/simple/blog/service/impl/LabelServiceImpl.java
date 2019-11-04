package com.simple.blog.service.impl;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.constant.HttpStatus;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LabelDTO;
import com.simple.blog.entity.LabelConfig;
import com.simple.blog.repository.LabelConfigRepository;
import com.simple.blog.repository.LabelRelationRepository;
import com.simple.blog.service.LabelService;
import com.simple.blog.service.RedisService;
import com.simple.blog.util.ClassConvertUtil;
import com.simple.blog.util.DataBaseUtil;
import com.simple.blog.util.HttpServletRequestUtil;
import com.simple.blog.util.JsonUtil;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LabelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    @Autowired
    private HttpServletRequestUtil httpServletRequestUtil;

    private final Object object = new Object();

    @Override
    public CommonDTO<LabelDTO> getSelectedLabel() {
        CommonDTO<LabelDTO> commonDTO = new CommonDTO<>();
        String username = httpServletRequestUtil.getUsername();
        if (StringUtils.isEmpty(username)) {
            commonDTO.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
            commonDTO.setMessage("token无效,请重新登陆");
            return commonDTO;
        }
        String person = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.PERSON_ATTENTION_LABEL + username);
        List labelNames;
        if (StringUtils.isEmpty(person)) {
            labelNames = labelRelationRepository.findLabelNameByUsernameAndSelectedNative(username, 1);
            redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.PERSON_ATTENTION_LABEL + username, JsonUtil.convertObject2String(labelNames));
        } else {
            labelNames = JsonUtil.convertString2Object(person, List.class);
        }
        List<LabelDTO> list = new ArrayList<>();
        labelNames.forEach(labelName -> {
            LabelDTO labelRelationDTO = LabelDTO.builder().labelName(String.valueOf(labelName)).build();
            list.add(labelRelationDTO);
        });
        commonDTO.setData(list);
        commonDTO.setTotal((long) list.size());
        return commonDTO;
    }

    @Override
    public CommonDTO<LabelDTO> getAllLabel(CommonVO<LabelVO> vo) {
        CommonDTO<LabelDTO> commonDTO = new CommonDTO<>();
        String labelFuzzyName = vo.getCondition().getLabelFuzzyName();
        List<LabelDTO> list = new ArrayList<>();
        LabelDTO labelDTO;
        // 根据 labelName 模糊查询相关结果
        List<LabelConfig> configList = labelConfigRepository.findAllByLabelNameLikeNative(labelFuzzyName);
        configList.sort((o1, o2) -> {
            String labelName1 = o1.getLabelName();
            String labelName2 = o2.getLabelName();
            return labelName1.compareTo(labelName2);
        });
        // 获取 此用户名下的关注标签
        String username = httpServletRequestUtil.getUsername();
        if (StringUtils.isEmpty(username)) {
            commonDTO.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
            commonDTO.setMessage("token无效,请重新登陆");
            return commonDTO;
        }
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
        String username = httpServletRequestUtil.getUsername();
        if (StringUtils.isEmpty(username)) {
            commonDTO.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
            commonDTO.setMessage("token无效,请重新登陆");
            return commonDTO;
        }
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
        String labelFuzzyName = commonVO.getCondition().getLabelFuzzyName();
        String username = httpServletRequestUtil.getUsername();
        if (StringUtils.isEmpty(username)) {
            commonDTO.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
            commonDTO.setMessage("token无效,请重新登陆");
            return commonDTO;
        }
        synchronized (object) {
            // 更新关注状态
            Integer isSuccess = labelRelationRepository.updateByUsernameAndLabelNameAndAttentionNative(username, labelName, attention);
            if (isSuccess == 1) {
                // 修改redis缓存中关注的标签
                List<String> labelNames = labelRelationRepository.findLabelNameByUsernameAndSelectedNative(username, 1);
                redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.PERSON_ATTENTION_LABEL + username, JsonUtil.convertObject2String(labelNames));
                // 修改全部标签的此标签的关注度
                String labelObj = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.ALL_LABEL + labelName);
                LabelDTO labelDTO = JsonUtil.convertString2Object(labelObj, LabelDTO.class);
                if (attention == 1) {
                    labelDTO.setNumOfAttention(labelDTO.getNumOfAttention() + 1);
                } else {
                    labelDTO.setNumOfAttention(labelDTO.getNumOfAttention() - 1);
                }
                redisService.setValue(CommonConstant.REDIS_CACHE + CommonConstant.ALL_LABEL + labelName, JsonUtil.convertObject2String(labelDTO));
            }
        }
        // 重新 查询 并返回结果
        List<LabelConfig> configList = labelConfigRepository.findAllByLabelNameLikeNative(labelFuzzyName);
        Map<String, String> allLabels = redisService.getValues(CommonConstant.REDIS_CACHE + CommonConstant.ALL_LABEL);
        String personalLabels = redisService.getValue(CommonConstant.REDIS_CACHE + CommonConstant.PERSON_ATTENTION_LABEL + username);
        List personalList = JsonUtil.convertString2Object(personalLabels, List.class);
        List<LabelDTO> list = new ArrayList<>();
        configList.forEach(config -> {
            LabelDTO dto = JsonUtil.convertString2Object(allLabels.get(config.getLabelName()), LabelDTO.class);
            if (personalList.contains(dto.getLabelName())) {
                dto.setIsAttention(1);
            } else {
                dto.setIsAttention(0);
            }
            list.add(dto);
        });
        list.sort((o1, o2) -> {
            String labelName1 = o1.getLabelName();
            String labelName2 = o2.getLabelName();
            return labelName1.compareTo(labelName2);
        });
        commonDTO.setData(list);
        commonDTO.setTotal((long) list.size());
        return commonDTO;
    }

    @Override
    public CommonDTO<LabelDTO> getAllLabelConfig() {
        CommonDTO<LabelDTO> commonDTO = new CommonDTO<>();
        Map<String, String> result = redisService.getValues(CommonConstant.REDIS_CACHE + CommonConstant.LABEL_CONFIG);
        List<LabelDTO> list = new ArrayList<>();
        if (result.isEmpty()) {
            List<LabelConfig> labelConfigList = labelConfigRepository.findAll();
            ClassConvertUtil.populateList(labelConfigList, list, LabelDTO.class);
        } else {
            LabelDTO labelDTO = null;
            for (Map.Entry<String, String> entry : result.entrySet()) {
                labelDTO = JsonUtil.convertString2Object(entry.getValue(), LabelDTO.class);
                list.add(labelDTO);
            }
        }
        commonDTO.setData(list);
        commonDTO.setTotal((long) list.size());
        return commonDTO;
    }
}
