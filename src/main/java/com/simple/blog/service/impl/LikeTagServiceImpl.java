package com.simple.blog.service.impl;

import com.simple.blog.constant.HttpStatus;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.dto.LikeTagDTO;
import com.simple.blog.entity.LikeTag;
import com.simple.blog.repository.LikeTagRepository;
import com.simple.blog.service.LikeTagService;
import com.simple.blog.util.ClassConvertUtil;
import com.simple.blog.util.HttpServletRequestUtil;
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
    @Autowired
    private HttpServletRequestUtil httpServletRequestUtil;

    @Override
    public CommonDTO<LikeTagDTO> getTag(CommonVO<LikeTagVO> commonVO) {
        CommonDTO<LikeTagDTO> commonDTO = new CommonDTO<>();
        String username = httpServletRequestUtil.getUsername();
        if (StringUtils.isEmpty(username)) {
            commonDTO.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
            commonDTO.setMessage("token无效,请重新登陆");
            return commonDTO;
        }
        String articleId = commonVO.getCondition().getArticleId();
        LikeTagDTO likeTagDTO = new LikeTagDTO();
        LikeTag likeTag = likeTagRepository.getNative(username, articleId);
        if (likeTag == null) {
            likeTag = new LikeTag();
            likeTag.setArticleId(articleId);
            likeTag.setHasRead(0);
            likeTag.setLove(0);
            likeTag.setUsername(username);
            likeTagRepository.save(likeTag);
        }
        ClassConvertUtil.populate(likeTag, likeTagDTO);
        Map<String, Object> dataExt = likeTagRepository.sumByArticleIdNative(articleId);
        commonDTO.setDataExt(dataExt);
        commonDTO.setData(Collections.singletonList(likeTagDTO));
        return commonDTO;
    }


    @Override
    public CommonDTO<LikeTagDTO> updateTag(CommonVO<LikeTagVO> commonVO) {
        CommonDTO<LikeTagDTO> commonDTO = new CommonDTO<>();
        String username = httpServletRequestUtil.getUsername();
        if (StringUtils.isEmpty(username)) {
            commonDTO.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
            commonDTO.setMessage("token无效,请重新登陆");
            return commonDTO;
        }
        String articleId = commonVO.getCondition().getArticleId();
        Integer love = commonVO.getCondition().getLove();
        LikeTagDTO likeTagDTO = new LikeTagDTO();
        // 更新 love 按钮； 否則更新是否已读按钮
        if (love != null) {
            if ("1".equals(String.valueOf(love))) {
                likeTagRepository.updateTagLikeByUsernameAndArticleIdNative(username, articleId, 0);
                likeTagDTO.setLove(0);
            } else {
                likeTagRepository.updateTagLikeByUsernameAndArticleIdNative(username, articleId, 1);
                likeTagDTO.setLove(1);
            }
            Map<String, Object> dataExt = likeTagRepository.sumByArticleIdNative(articleId);
            commonDTO.setDataExt(dataExt);
        } else {
            likeTagRepository.updateHasReadByUsernameAndArticleIdNative(username, articleId);
        }
        commonDTO.setData(Collections.singletonList(likeTagDTO));
        return commonDTO;
    }
}
