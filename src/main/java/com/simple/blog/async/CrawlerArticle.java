package com.simple.blog.async;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.google.common.collect.ImmutableMap;
import com.simple.blog.entity.Blog;
import com.simple.blog.repository.BlogRepository;
import com.simple.blog.repository.LabelConfigRepository;
import com.simple.blog.util.DateUtil;
import com.simple.blog.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 爬虫文章
 *
 * @author songning
 * @date 2019/8/29
 * description
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class CrawlerArticle {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private LabelConfigRepository labelConfigRepository;


    private Map<String, String> toutiaoUrls = ImmutableMap.<String, String>builder()
            .put("热点", "https://www.toutiao.com/ch/news_hot/")
            .put("科技", "https://www.toutiao.com/ch/news_tech/")
            .put("娱乐", "https://www.toutiao.com/ch/news_entertainment/")
            .put("游戏", "https://www.toutiao.com/ch/news_game/")
            .put("体育", "https://www.toutiao.com/ch/news_sports/")
            .put("财经", "https://www.toutiao.com/ch/news_finance/")
            .put("搞笑", "https://www.toutiao.com/ch/funny/")
            .put("军事", "https://www.toutiao.com/ch/news_military/")
            .put("国际", "https://www.toutiao.com/ch/news_world/")
            .put("时尚", "https://www.toutiao.com/ch/news_fashion/")
            .put("旅游", "https://www.toutiao.com/ch/news_travel/")
            .put("探索", "https://www.toutiao.com/ch/news_discovery/")
            .put("育儿", "https://www.toutiao.com/ch/news_baby/")
            .put("养生", "https://www.toutiao.com/ch/news_regimen/")
            .put("美文", "https://www.toutiao.com/ch/news_essay/")
            .put("历史", "https://www.toutiao.com/ch/news_history/")
            .put("美食", "https://www.toutiao.com/ch/news_food/").build();

    @Scheduled(cron = "0 0/30 * * * ?")
    public void theftArticle() throws Exception {
        this.theftToutiao();
    }

    private void theftToutiao() {
        Document html;
        List<String> authors = blogRepository.getAllAuthorNative();
        List<String> labels = labelConfigRepository.findAllLabelNameNative();
        String random = RandomUtil.getRandom(0, authors.size() - 1);
        for (Map.Entry<String, String> entry : toutiaoUrls.entrySet()) {
            log.info("~~~开始拉取今日头条 {} 新闻:~~~", entry.getKey());
            html = getHtmlFromUrl(entry.getValue(), true);
            Document contentHtml;
            Blog blog;
            for (Element a : html.select("a[href~=/group/.*]:not(.comment)")) {
                String href = "https://www.toutiao.com" + a.attr("href");
                contentHtml = getHtmlFromUrl(href, true);
                String articleInfoHtml = contentHtml.body().getElementsByTag("script").get(3).html();
                JSONObject articleObject = JSON.parseObject("{" + articleInfoHtml.substring(33, articleInfoHtml.length() - 12).replace(".slice(6, -6).replace(/<br \\/>/ig, '')", "").replace(".slice(6, -6)", "").replace("\\", "\\\\").replace("\\\\\"", "\\\"") + "}");
                String title = StringEscapeUtils.unescapeHtml4(articleObject.getJSONObject("articleInfo").getString("title")).replace("\\\\", "\\");
                Map<String, Object> totalMap = blogRepository.countArticleByTitleNative(title);
                if (totalMap.get("total").equals(new BigInteger("0"))) {
                    String author = authors.get(Integer.parseInt(random));
                    Integer readTimes = Integer.parseInt(RandomUtil.getRandom(1, 1000));
                    String kinds = labels.get(Integer.parseInt(RandomUtil.getRandom(0, labels.size() - 1)));
                    Date updateTime = DateUtil.getBeforeByCurrentTime(Integer.parseInt(RandomUtil.getRandom(1, 23)));
                    String content = StringEscapeUtils.unescapeEcmaScript(StringEscapeUtils.unescapeHtml4(articleInfoHtml.substring(articleInfoHtml.indexOf("content") + 10, articleInfoHtml.lastIndexOf(".slice(6, -6),") - 1))).replaceAll("&gt;", ">").replaceAll("&lt;", "<");
                    String summary = StringEscapeUtils.unescapeHtml4(articleObject.getJSONObject("shareInfo").getString("abstract").replace("\\\\", "\\")).replaceAll("&gt;", ">").replaceAll("&lt;", "<");
                    blog = Blog.builder().author(author).title(title).readTimes(readTimes).kinds(kinds).updateTime(updateTime).content(content).summary(summary).build();
                    blogRepository.save(blog);
                }
            }
        }
    }

    private Document getHtmlFromUrl(String url, boolean useHtmlUnit) {
        Document html = null;
        if (useHtmlUnit) {
            WebClient webClient = new WebClient(BrowserVersion.CHROME);
            webClient.getOptions().setJavaScriptEnabled(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setActiveXNative(false);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setTimeout(10000);
            HtmlPage rootPage;
            try {
                rootPage = webClient.getPage(url);
                webClient.waitForBackgroundJavaScript(10000);
                String htmlString = rootPage.asXml();
                html = Jsoup.parse(htmlString);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                webClient.close();
            }
        } else {
            try {
                html = Jsoup.connect(url).userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").get();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return html;
    }
}
