package com.simple.blog.service.impl;

import com.simple.blog.constant.CommonConstant;
import com.simple.blog.dto.BlogDTO;
import com.simple.blog.dto.BloggerDTO;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.entity.Blog;
import com.simple.blog.entity.EsBlog;
import com.simple.blog.entity.SystemConfig;
import com.simple.blog.repository.BlogRepository;
import com.simple.blog.repository.EsBlogRepository;
import com.simple.blog.service.BlogService;
import com.simple.blog.service.RedisService;
import com.simple.blog.util.ClassConvertUtil;
import com.simple.blog.util.DateUtil;
import com.simple.blog.util.MapConvertEntityUtil;
import com.simple.blog.vo.BlogVO;
import com.simple.blog.vo.CommonVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author sn
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private EsBlogRepository esBlogRepository;
    @Autowired
    private RedisService redisService;

    private CrudRepository getDataSource() {
        SystemConfig systemConfig = (SystemConfig) redisService.getValue(CommonConstant.REDIS_CACHE, CommonConstant.SYSTEM_CONFIG, "dataSource");
        String value = systemConfig.getConfigValue();
        if ("mysql".equals(value)) {
            return blogRepository;
        } else {
            return esBlogRepository;
        }
    }

    @Override
    public CommonDTO<BlogDTO> saveArticle(CommonVO<BlogVO> commonVO) {
        String title = commonVO.getCondition().getTitle();
        String content = commonVO.getCondition().getContent();
        String summary = commonVO.getCondition().getSummary();
        String kinds = commonVO.getCondition().getKinds();
        Integer readTimes = commonVO.getCondition().getReadTimes();
        String author = commonVO.getCondition().getAuthor();
        EsBlog blog = EsBlog.builder().title(title).kinds(kinds).summary(summary).author(author).content(content).updateTime(new Date()).readTimes(readTimes).build();
        getDataSource().save(blog);
        return new CommonDTO<>();
    }

    @Override
    public CommonDTO<BlogDTO> getAbstract(CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = new CommonDTO<>();
        Integer recordStartNo = commonVO.getRecordStartNo();
        Integer pageRecordNum = commonVO.getPageRecordNum();
        String kinds = commonVO.getCondition().getKinds();
        Sort sort = new Sort(Sort.Direction.DESC, "update_time");
        Pageable pageable = PageRequest.of(recordStartNo, pageRecordNum, sort);
        Page<Object[]> blogPage = blogRepository.findAbstract(kinds, pageable);
        List<Object[]> src = blogPage.getContent();
        Long total = blogPage.getTotalElements();
        List<BlogDTO> target = new ArrayList<>();
        try {
            ClassConvertUtil.castEntityList(src, target, BlogDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        commonDTO.setData(target);
        commonDTO.setTotal(total);
        return commonDTO;
    }

    @Override
    public CommonDTO<BlogDTO> getContent(CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = new CommonDTO<>();
        String id = commonVO.getCondition().getId();
        Map<String, Object> blog = blogRepository.findByIdNative(id);
        Integer readTimes = (Integer) blog.get("readTimes");
        blogRepository.updateReadTimes(id, ++readTimes);
        BlogDTO blogDTO = null;
        try {
            blogDTO = (BlogDTO) MapConvertEntityUtil.mapToEntity(BlogDTO.class, blog);
        } catch (Exception e) {
            e.printStackTrace();
        }
        commonDTO.setData(Collections.singletonList(blogDTO));
        commonDTO.setTotal(1L);
        return commonDTO;
    }

    @Override
    public void theftContent(CommonVO<BlogVO> commonVO) throws Exception {
        String url = commonVO.getCondition().getUrl();
        // 爬虫阿里云
        if ("https://yq.aliyun.com".equals(url)) {
            Document document = Jsoup.connect(url + "/articles").get();
            List<String> articleUrls = document.getElementsByClass("item-box").stream().map(o -> o.getElementsByTag("a").get(0).attr("href")).collect(Collectors.toList());
            for (int i = 0; i < articleUrls.size(); i++) {
                Document doc = Jsoup.connect(url + articleUrls.get(i) + "?type=2").get();
                String title = doc.getElementsByClass("blog-title").html();
                String content = doc.getElementsByClass("markdown-body").html();
                Integer readTimes = Integer.parseInt(doc.getElementsByClass("b-watch").html().substring(2));
                String author = doc.getElementsByClass("b-author").html();
                List<String> tags = doc.getElementsByClass("label-item").stream().map(o -> o.getElementsByTag("span").html()).collect(Collectors.toList());
                StringBuilder tagStr = new StringBuilder();
                tags.forEach(o -> {
                    tagStr.append(o);
                    tagStr.append(",");
                });
                String kinds = tagStr.substring(0, tagStr.length() - 1) + ",前端，后端，数据库，热门，关注";
                Date time = DateUtil.strToDate(doc.getElementsByClass("b-time").html(), "yyyy-MM-dd HH:mm:ss");
                String summary = Jsoup.parse(Jsoup.connect("https://yq.aliyun.com/articles/708486?type=2").get().getElementsByClass("markdown-body").get(0).getElementsByTag("p").get(0).html()).text();
                Blog blog = Blog.builder().title(title).content(content).summary(summary).readTimes(readTimes).kinds(kinds).author(author).updateTime(time).build();
                blogRepository.save(blog);
            }
        } else if ("https://tech.meituan.com/".equals(url)) {
            // 爬虫美团
            for (int i = 2; i < 3; i++) {
                Document document = Jsoup.connect(url + "/page/" + i + ".html").get();
                List<String> articleUrls = document.getElementsByClass("post-title").stream().map(o -> o.getElementsByTag("a").get(0).attr("href")).collect(Collectors.toList());
                for (int j = 0; j < articleUrls.size(); j++) {
                    Document doc = Jsoup.connect(articleUrls.get(i)).get();
                    String title = doc.getElementsByClass("post-title").get(0).getElementsByTag("a").html();
                    String read = doc.getElementsByClass("m-post-count").get(0).getElementsByTag("span").get(0).childNode(1).toString();
                    String time = read.substring(0, read.length() - 1);
                    Integer readTimes = Integer.parseInt(time);
                    String content = doc.getElementsByClass("post-content").html();
                    String author = doc.getElementsByClass("m-post-nick").get(0).childNode(0).toString();
                    author = author.substring(3);
                    String kinds;
                    if (0 == j % 5) {
                        kinds = "HTML";
                    } else if (1 == j % 5) {
                        kinds = "Git";
                    } else if (2 == j % 5) {
                        kinds = "机器学习";
                    } else if (3 == j % 5) {
                        kinds = "MySQL";
                    } else {
                        kinds = "iOS";
                    }
                    EsBlog esBlog = EsBlog.builder().title(title).content(content).summary(title + Math.random()).readTimes(readTimes).kinds(kinds).author(author).updateTime(new Date()).build();
                    esBlogRepository.save(esBlog);
                }
            }
        } else {
            throw new Exception("爬虫文章失败，请输入正确url！");
        }
    }

    @Override
    public CommonDTO<BlogDTO> getHotArticle(CommonVO<BlogVO> commonVO) {
        CommonDTO<BlogDTO> commonDTO = new CommonDTO<>();
        String kinds = commonVO.getCondition().getKinds();
        List<Map<String, Object>> list = blogRepository.findHotArticle(kinds);
        List<BlogDTO> blogDTOS = new ArrayList<>();
        list.forEach(item -> {
            try {
                BlogDTO blogDTO = (BlogDTO) MapConvertEntityUtil.mapToEntity(BlogDTO.class, item);
                blogDTOS.add(blogDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        commonDTO.setData(blogDTOS);
        return commonDTO;
    }

    @Override
    public CommonDTO<BlogDTO> deleteAllArticle() {
        esBlogRepository.deleteAll();
        return new CommonDTO<>();
    }
}