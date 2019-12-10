package com.simple.blog.service.impl;

import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LikeTagDTO;
import com.simple.blog.repository.LikeTagRepository;
import com.simple.blog.service.LikeTagService;
import com.simple.blog.vo.CommonVO;
import com.simple.blog.vo.LikeTagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author songning
 * @date 2019/9/26
 * description
 */
@Service
public class LikeTagServiceImpl implements LikeTagService {

    @Autowired
    private LikeTagRepository likeTagRepository;

    @Override
    public CommonDTO<LikeTagDTO> getTag(CommonVO<LikeTagVO> commonVO) {
        CommonDTO<LikeTagDTO> commonDTO = new CommonDTO<>();
        String username = commonVO.getCondition().getUsername();
        String articleId = commonVO.getCondition().getArticleId();
        LikeTagDTO likeTagDTO = new LikeTagDTO();
        Integer love = likeTagRepository.findLoveByUsernameAndArticleIdNative(username, articleId);
        synchronized (this) {
            if (StringUtils.isEmpty(love)) {
                likeTagRepository.insertLikeTagByUsernameAndArticleIdNative(username, articleId, 0);
                likeTagDTO.setLove(0);
            } else {
                love = likeTagRepository.findLoveByUsernameAndArticleIdNative(username, articleId);
                likeTagDTO.setLove(love);
            }
        }
        Map<String, Object> dataExt = likeTagRepository.sumByArticleIdNative(articleId);
        likeTagDTO.setLove(love);
        commonDTO.setDataExt(dataExt);
        commonDTO.setData(Collections.singletonList(likeTagDTO));
        return commonDTO;
    }


    @Override
    public CommonDTO<LikeTagDTO> updateTag(CommonVO<LikeTagVO> commonVO) {
        CommonDTO<LikeTagDTO> commonDTO = new CommonDTO<>();
        String username = commonVO.getCondition().getUsername();
        String articleId = commonVO.getCondition().getArticleId();
        Integer love = commonVO.getCondition().getLove();
        LikeTagDTO likeTagDTO = new LikeTagDTO();
        if ("1".equals(String.valueOf(love))) {
            likeTagRepository.updateTagLikeByUsernameAndArticleIdNative(username, articleId, 0);
            likeTagDTO.setLove(0);
        } else {
            likeTagRepository.updateTagLikeByUsernameAndArticleIdNative(username, articleId, 1);
            likeTagDTO.setLove(1);
        }
        Map<String, Object> dataExt = likeTagRepository.sumByArticleIdNative(articleId);
        commonDTO.setData(Collections.singletonList(likeTagDTO));
        commonDTO.setDataExt(dataExt);
        return commonDTO;
    }
}
