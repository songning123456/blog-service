package com.simple.blog.async;

import com.simple.blog.entity.Blog;
import com.simple.blog.repository.BlogRepository;
import com.simple.blog.util.DateUtil;
import com.simple.blog.util.RandomUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author songning
 * @date 2019/8/29
 * description
 */
@Component
@Configuration
@EnableScheduling
public class ScheduleTask {

    private  List<String> labels = Arrays.asList(
            "前端", "后端", "JavaScript", "GitHub", "架构",
            "代码规范", "面试", "算法", "Android", "CSS",
            "程序员", "Vue.js", "Java", "Node.js", "数据库",
            "设计模式", "设计", "前端框架", "HTML", "开源",
            "产品", "Linux", "React.js", "Git", "Python",
            "IOS", "人工智能", "微信小程序", "Webpack", "全栈",
            "微信", "ECMAScript 6", "MYSQL", "HTTP", "Google",
            "正则表达式", "机器学习", "黑客", "Jquery", "响应式设计",
            "APP", "创业", "Chrome", "Nginx", "编程语言",
            "命令行", "Doctor", "Redis", "产品经理", "Android Studio",
            "Angular.js", "Mac", "React Native", "BootStrap", "Apple",
            "图片资源", "Photoshop", "PHP", "API", "Sublime Text",
            "设计师", "数据挖掘", "操作系统", "阿里巴巴", "MongoDB",
            "gradle", "Material Design", "数据可视化", "安全", "招聘",
            "Go", "Swift", "Vuex", "MVVM", "RxJava",
            "Xcode", "敏捷开发", "运维", "Markdown", "动效",
            "字体", "腾讯", "Objective-C", "云计算", "Spring",
            "运营", "物联网", "Canvas", "深度学习", "Icon",
            "爬虫", "C++", "虚拟现实", "HTTPS", "Debug",
            "Eclipse", "电子书", "Ubuntu", "Sketch", "翻译",
            "NPM", "微服务", "JSON", "测试", "配色",
            "Ajax", "DOM", "C", "源码", "Facebook",
            "VIM", "SCSS", "稀土", "TypeScript", "Apache",
            "游戏", "Redux", "maven", "Kotlin", "Visual Studio Code",
            "负载均衡", "SVG", "Windows", "SEC", "区域链",
            "支付宝", "函数式编程", "Gulp", "增强现实", "Microsoft",
            "SQLite", "Flutter", "浏览器", "Express", "Unity 3D",
            "SQL", "远程工作", "Firefox", "APK", "Atom",
            "Promise", "Webkit", "IntelliJ IDEA", "Hadoop", "Spring boot",
            "嵌入式", "JVM", "机器人", "编译器", "神经网络",
            "响应式编程", "投资", "Django", "科幻", "百度",
            "比特币", "单元测试", "flexbox", "Java EE"
    );

    @Autowired
    private BlogRepository blogRepository;

    /**
     * 每天10，12，16点触发该事件
     *
     * @throws Exception
     */
    @Scheduled(cron = "0 0 1 * * *")
    public void theftArticle() throws Exception {
        this.theftMeituan();
        this.theftBoke();
    }

    private void theftMeituan() throws Exception {
        String url = "https://tech.meituan.com/";
        List<String> authors = Arrays.asList("吃顿好的", "cest mom seul", "zhou", "重零开始");
        String random = RandomUtil.getRandom(2, 18);
        // 爬虫美团
        Document document = Jsoup.connect(url + "/page/" + random + ".html").get();
        List<String> articleUrls = document.getElementsByClass("post-title").stream().map(o -> o.getElementsByTag("a").get(0).attr("href")).collect(Collectors.toList());
        for (int j = 0; j < articleUrls.size(); j++) {
            Document doc = Jsoup.connect(articleUrls.get(Integer.parseInt(random))).get();
            String title = doc.getElementsByClass("post-title").get(0).getElementsByTag("a").html();
            String readTimes = RandomUtil.getRandom(1, 1000);
            String content = doc.getElementsByClass("post-content").html();
            String author = authors.get(Integer.parseInt(RandomUtil.getRandom(0, 3)));
            String kinds = labels.get(Integer.parseInt(RandomUtil.getRandom(0, labels.size() - 1)));
            Date updateTime = DateUtil.getBeforeByCurrentTime(Integer.parseInt(RandomUtil.getRandom(1, 23)));
            final String[] summary = new String[1];
            doc.getElementsByClass("post-content").get(0).getElementsByClass("content").get(0).getElementsByTag("p").forEach(item -> {
                if (!StringUtils.isEmpty(item.html()) && StringUtils.isEmpty(summary[0])) {
                    summary[0] = item.html();
                }
            });
            Blog blog = Blog.builder().title(title).content(content).summary(summary[0]).readTimes(Integer.parseInt(readTimes)).kinds(kinds).author(author).updateTime(updateTime).build();
            blogRepository.save(blog);
        }
    }

    private void theftBoke() throws Exception {
        String url = "https://www.boke.la/wenzhang/";
        List<String> authors = Arrays.asList("吃顿好的", "cest mom seul", "zhou", "重零开始");
        String random = RandomUtil.getRandom(2, 35);
        Document document = Jsoup.connect(url + random + "/").get();
        List<String> articleUrls = document.getElementsByClass("news").get(0).getElementsByTag("li").stream().map(o -> o.getElementsByTag("a").get(0).attr("href")).collect(Collectors.toList());
        for (String articleUrl : articleUrls) {
            Document doc = Jsoup.connect(articleUrl).get();
            String title = doc.getElementsByClass("zwtit2").get(0).getElementsByTag("h3").html();
            String readTimes = RandomUtil.getRandom(1, 1000);
            String kinds = labels.get(Integer.parseInt(RandomUtil.getRandom(0, labels.size() - 1)));
            final String[] summary = new String[1];
            doc.getElementsByClass("zhengwen").get(0).getElementsByTag("p").forEach(item -> {
                if (!StringUtils.isEmpty(item.html()) && StringUtils.isEmpty(summary[0])) {
                    summary[0] = item.html();
                }
            });
            String content = doc.getElementsByClass("zhengwen").get(0).getElementsByTag("p").html();
            String author = authors.get(Integer.parseInt(RandomUtil.getRandom(0, 3)));
            Date updateTime = DateUtil.getBeforeByCurrentTime(Integer.parseInt(RandomUtil.getRandom(1, 12)));
            Blog blog = Blog.builder().title(title).summary(summary[0]).content(content).readTimes(Integer.parseInt(readTimes)).kinds(kinds).author(author).updateTime(updateTime).build();
            blogRepository.save(blog);
        }
    }
}
