package com.simple.blog.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.ThirdPartDTO;
import com.simple.blog.service.ThirdPartService;
import com.simple.blog.util.HttpUtil;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.ThirdPartVO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author songning
 * @date 2019/12/23
 * description
 */
@Service
public class ThirdPartServiceImpl implements ThirdPartService {

    @Override
    public CommonDTO<ThirdPartDTO> getGitHub(CommonVO<ThirdPartVO> commonVO) {
        CommonDTO<ThirdPartDTO> commonDTO = new CommonDTO<>();
        Map<String, Object> params = new HashMap<>(2);
        params.put("client_id", commonVO.getCondition().getClientId());
        params.put("client_secret", commonVO.getCondition().getClientSecret());
        params.put("code", commonVO.getCondition().getCode());
        String result1 = HttpUtil.doPost(commonVO.getCondition().getAccessTokenURL(), params);
        String accessToken = result1.split("&")[0].substring(13);
        String url = commonVO.getCondition().getUserURL() + "?access_token=" + accessToken;
        String result2 = HttpUtil.doGet(url);
        Map<String, Object> result = new HashMap<>(2);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            result = objectMapper.readValue(result2, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        commonDTO.setDataExt(result);
        return commonDTO;
    }
}
