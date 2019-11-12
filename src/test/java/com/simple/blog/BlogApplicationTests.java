package com.simple.blog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.simple.blog.constant.CommonConstant;
import com.simple.blog.dto.CommonDTO;
import com.simple.blog.entity.*;
import com.simple.blog.repository.*;
import com.simple.blog.util.*;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.io.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private BloggerRepository bloggerRepository;
    @Autowired
    private SystemConfigRepository systemConfigRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PersonalInformationRepository personalInformationRepository;
    @Autowired
    private LabelConfigRepository labelConfigRepository;
    @Autowired
    private LabelRelationRepository labelRelationRepository;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private List<String> usernames = Arrays.asList("shenkeye", "shijie", "haozhou", "songning");

    private List<String> authors = Arrays.asList("安静的猫", "凌晨三点半");

    private List<String> labels = Arrays.asList(
            "前端", "后端", "JavaScript", "GitHub", "架构", "代码规范", "面试", "算法", "Android", "CSS",
            "程序员", "Vue.js", "Java", "Node.js", "数据库", "设计模式", "设计", "前端框架", "HTML", "开源",
            "产品", "Linux", "React.js", "Git", "Python", "IOS", "人工智能", "微信小程序", "Webpack", "全栈",
            "微信", "ECMAScript 6", "MYSQL", "HTTP", "Google", "正则表达式", "机器学习", "黑客", "Jquery", "响应式设计",
            "APP", "创业", "Chrome", "Nginx", "编程语言", "命令行", "Doctor", "Redis", "产品经理", "Android Studio",
            "Angular.js", "Mac", "React Native", "BootStrap", "Apple", "图片资源", "Photoshop", "PHP", "API", "Sublime Text",
            "设计师", "数据挖掘", "操作系统", "阿里巴巴", "MongoDB", "gradle", "Material Design", "数据可视化", "安全", "招聘",
            "Go", "Swift", "Vuex", "MVVM", "RxJava", "Xcode", "敏捷开发", "运维", "Markdown", "动效",
            "字体", "腾讯", "Objective-C", "云计算", "Spring", "运营", "物联网", "Canvas", "深度学习", "Icon",
            "爬虫", "C++", "虚拟现实", "HTTPS", "Debug", "Eclipse", "电子书", "Ubuntu", "Sketch", "翻译",
            "NPM", "微服务", "JSON", "测试", "配色", "Ajax", "DOM", "C", "源码", "Facebook",
            "VIM", "SCSS", "稀土", "TypeScript", "Apache", "游戏", "Redux", "maven", "Kotlin", "Visual Studio Code",
            "负载均衡", "SVG", "Windows", "SEC", "区域链", "支付宝", "函数式编程", "Gulp", "增强现实", "Microsoft",
            "SQLite", "Flutter", "浏览器", "Express", "Unity 3D", "SQL", "远程工作", "Firefox", "APK", "Atom",
            "Promise", "Webkit", "IntelliJ IDEA", "Hadoop", "Spring boot", "嵌入式", "JVM", "机器人", "编译器", "神经网络",
            "响应式编程", "投资", "Django", "科幻", "百度", "比特币", "单元测试", "flexbox", "Java EE"
    );

    // 请求超时时间，30秒
    public static final int TIME_OUT = 30 * 1000;
    // 模拟浏览器请求头信息
    public static Map<String, String> headers = new HashMap<>();

    static {
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 5.1; zh-CN) AppleWebKit/535.12 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/535.12");
        headers.put("Accept", "text/html");
        headers.put("Accept-Language", "zh-CN,zh");
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void nameConvert() {
        String str1 = "createTime";
        String str2 = "create_time";

        String result1 = NameConvertUtil.camelToUnderline(str1);
        String result2 = NameConvertUtil.underlineToCamel(str2);

        log.info("驼峰转下划线命名: {}", result1);
        log.info("下划线转驼峰: {}", result2);
    }

    /**
     * 把数据按指定格式输出成文件
     */
    @Test
    public void outputTxt() {
//        List<String> target = new ArrayList<>();
//        List<Map<String, Object>> src = blogRepository.getAllAuthorAndTitle();
//        src.forEach(item -> {
//            StringBuilder stringBuilder = new StringBuilder();
//            for (Map.Entry<String, Object> entry : item.entrySet()) {
//                if (stringBuilder.length() == 0) {
//                    stringBuilder.append("{'").append(entry.getKey()).append("':'").append(entry.getValue()).append("',");
//                } else {
//                    stringBuilder.append("'").append(entry.getKey()).append("':'").append(entry.getValue()).append("'},");
//                }
//            }
//            target.add(stringBuilder.toString());
//        });
//        String fileName = "D:\\haiyan-data/blogData.txt";
//        FileUtil.appendDataToFile(target, fileName);
//        long fileLength = FileUtil.getFileSize(new File(fileName));
//        System.out.println(fileLength);
    }

    @Test
    public void insertUsers() {
        Users songning = Users.builder().username("songning").password("123456").role("ADMIN").build();
        Users haozhou = Users.builder().username("haozhou").password("123456").role("USER").build();
        Users shijie = Users.builder().username("shijie").password("123456").role("USER").build();
        Users shenkeye = Users.builder().username("shenkeye").password("123456").role("USER").build();
        List<Users> usersList = Arrays.asList(songning, haozhou, shenkeye, shijie);
//        usersRepository.saveAll(usersList);
    }

    /**
     * 从excel 中获取数据
     *
     * @throws Exception
     */
    @Test
    public void readExcel() throws Exception {
        File srcFile = new File("D:\\haiyan-data\\vehicle-brand\\vehicle.xls");
        File targetFile = new File("D:\\haiyan-data\\vehicle-brand\\vehicle.txt");
        List<String> total = new ArrayList<>();
        List<List> excelList = FileUtil.readExcel(srcFile);
        List<String> src = new ArrayList<>();
        excelList.forEach(row -> {
            row.forEach(item -> {
                if (RegularUtil.isInteger(item.toString())) {
                    src.add(item.toString());
                }
            });
        });
        List<String> target = FileUtil.readTxt(targetFile);
        List<String> tar = new ArrayList<>();
        target.forEach(item -> {
            String[] temp = item.split(",");
            String val = temp[0].substring(13);
            tar.add(val);
        });
        src.forEach(item -> {
            if (!tar.contains(item)) {
                total.add(item);
            }
        });
        System.out.println(total);
    }

    /**
     * 汉语拼音转换
     *
     * @throws Exception
     */
    @Test
    public void hypyConvert() throws Exception {
        HanyuPinyinOutputFormat hypy = new HanyuPinyinOutputFormat();
        // 输出的拼音的大小写
        hypy.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //  通过数字标注声调
        hypy.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);
        // 处理u ü
        hypy.setVCharType(HanyuPinyinVCharType.WITH_V);
        String result = PinyinHelper.toHanyuPinyinStringArray("张新华".charAt(2), hypy)[0];
        log.info(result);
    }

    /**
     * 测试相同查询条件文档是否完全一致(根据motorVehicleID查询)
     */
    @Test
    public void testFileEqual() throws Exception {
        File srcFile = new File("D:\\haiyan-data\\scollID\\src.txt");
        File targetFile = new File("D:\\haiyan-data\\scollID\\target.txt");
        List<String> srcResult = FileUtil.readTxtByCondition(srcFile, "MotorVehicleID");
        List<String> targetResult = FileUtil.readTxtByCondition(targetFile, "MotorVehicleID");
        List<String> record = new ArrayList<>();
        boolean compare = true;
        for (int i = 0; i < 60; i++) {
            if (!srcResult.get(i).equals(targetResult.get(i))) {
                compare = false;
                break;
            }
        }
        log.info("查询结果是否排序一致: {}", compare);
        srcResult.forEach(src -> {
            if (!targetResult.contains(src)) {
                record.add(src);
            }
        });
        log.info("两次查询比较结果: {}", record);
    }

    @Test
    public void matchPattern() {
        String testString = "java怎么mysql利用正则表达式从mysql给定的字符串中mysql取出匹配规则字符串";
       /* Pattern pattern = Pattern.compile("mysql");
        Matcher matcher = pattern.matcher(testString);
        while (matcher.find()) {
            int start1 = matcher.start();
            int end1 = matcher.end();
            String value = matcher.group();
            System.out.println(start1 + "---" + end1 + "---" + value);
        }*/
        this.matchPattern(testString, "mysql");
    }

    private List<String> matchPattern(String content, String regex) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(content);
        int len = content.length();
        while (matcher.find()) {
            StringBuilder stringBuilder = new StringBuilder();
            int start = matcher.start();
            int end = matcher.end();
            int startBefore, endAfter;
            if (start > 8) {
                startBefore = start - 8;
            } else {
                startBefore = 0;
            }
            if (end + 8 < len) {
                endAfter = end + 8;
            } else {
                endAfter = len;
            }
            String text1 = content.substring(startBefore, start);
            String text2 = "<span style='color: #ffa500;font-weight: bold;font-size: 16px !important;'>" + matcher.group() + "</span>";
            String text3 = content.substring(end + 1, endAfter);
            result.add(stringBuilder.append("...").append(text1).append(text2).append(text3).append("...").toString());
        }
        return result;
    }

    @Test
    public void insertBlogger() {
        Blogger blogger = new Blogger();
        blogger.setAuthor("吃顿好的");
        blogger.setRealName("沈克野");
        blogger.setGender("男");
        blogger.setAge(25);
        blogger.setProfession("公务员");
        blogger.setTelephone("15550779134");
        blogger.setEmail("3123476563@qq.com");
        blogger.setMotto("好好学习，天天向上");
        blogger.setHeadPortrait("headPortrait/songning.svg");
        blogger.setUsername("shenkeye");
        blogger.setUpdateTime(new Date());
//        bloggerRepository.save(blogger);
    }

    @Test
    public void insertPersonalInfo() {
        List<PersonalInformation> list = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            PersonalInformation personalInformation = new PersonalInformation();
            personalInformation.setAuthor("");
            personalInformation.setInfoType("");
            personalInformation.setMechanism("");
            personalInformation.setPosition("");
            personalInformation.setIntroduction("");
            personalInformation.setStartTime(new Date());
            personalInformation.setEndTime(new Date());
            list.add(personalInformation);
        }
//        personalInformationRepository.saveAll(list);
    }

    @Test
    public void insertSystemConfig() {
        List<String> list = Arrays.asList("songning", "shenkeye", "shijie", "haozhou");
        list.forEach(username -> {
            SystemConfig systemConfig = new SystemConfig();
            systemConfig.setUsername(username);
            systemConfig.setConfigKey("dataBase");
            systemConfig.setConfigValue("MYSQL");
            systemConfig.setValueDescription("动态切换数据源");
//            systemConfigRepository.save(systemConfig);
        });
    }

    @Test
    public void insertLabelConfig() {
        labels.forEach(label -> {
            LabelConfig labelConfig = new LabelConfig();
            labelConfig.setLabelName(label);
            labelConfig.setLabelPhoto("");
//            labelConfigRepository.save(labelConfig);
        });
    }

    @Test
    public void insertLabelRelation() {
        Set<String> sets = new HashSet<>(labels);
        usernames.forEach(username -> {
            sets.forEach(label -> {
                LabelRelation labelRelation = new LabelRelation();
                labelRelation.setUsername(username);
                labelRelation.setLabelName(label);
                Integer attention = Integer.parseInt(RandomUtil.getRandom(0, 1));
                labelRelation.setAttention(attention);
//                labelRelationRepository.save(labelRelation);
            });
        });
    }

    @Test
    public void testRedisSetTimeout() throws InterruptedException {
//        stringRedisTemplate.opsForValue().set("timeOutKey", "timeOut", 5, TimeUnit.SECONDS);
//        String timeOutValue = stringRedisTemplate.opsForValue().get("timeOutKey") + "";
//        System.out.println("通过set(K key, V value, long timeout, TimeUnit unit)方法设置过期时间，过期之前获取的数据:" + timeOutValue);
//        Thread.sleep(5 * 1000);
//        timeOutValue = stringRedisTemplate.opsForValue().get("timeOutKey") + "";
//        System.out.print(",等待10s过后，获取的值:" + timeOutValue);
    }

    @Test
    public void testLog() {
        log.info("测试日志是否生效");
    }

    @Test
    public void insertMeituan() throws Exception {
        String url = "https://tech.meituan.com/";
//        String random = RandomUtil.getRandom(2, 18);
        // 爬虫美团
        for (int k = 11; k <= 19; k++) {
            Document document = Jsoup.connect(url + "/page/" + k + ".html").get();
            List<String> articleUrls = document.getElementsByClass("post-title").stream().map(o -> o.getElementsByTag("a").get(0).attr("href")).collect(Collectors.toList());
            for (int j = 0; j < articleUrls.size(); j++) {
                Document doc = Jsoup.connect(articleUrls.get(j)).get();
                String title = doc.getElementsByClass("post-title").get(0).getElementsByTag("a").html();
                String readTimes = RandomUtil.getRandom(1, 1000);
                String content = doc.getElementsByClass("post-content").html();
                String author = authors.get(1);
                String kinds = labels.get(Integer.parseInt(RandomUtil.getRandom(0, labels.size() - 1)));
                Date updateTime = DateUtil.getBeforeByCurrentTime(Integer.parseInt(RandomUtil.getRandom(1, 23)));
                final String[] summary = new String[1];
                doc.getElementsByClass("post-content").get(0).getElementsByClass("content").get(0).getElementsByTag("p").forEach(item -> {
                    if (!StringUtils.isEmpty(item.html()) && StringUtils.isEmpty(summary[0])) {
                        summary[0] = item.html();
                    }
                });
                Blog blog = Blog.builder().title(title).content(content).summary(summary[0]).readTimes(Integer.parseInt(readTimes)).kinds(kinds).author(author).updateTime(updateTime).build();
//                blogRepository.save(blog);
            }
        }
    }

    @Test
    public void insertBoke() throws Exception {
        String url = "https://www.boke.la/wenzhang/";
//        String random = RandomUtil.getRandom(2, 35);
        for (int k = 18; k <= 35; k++) {
            Document document = Jsoup.connect(url + k + "/").get();
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
                String author = authors.get(1);
                Date updateTime = DateUtil.getBeforeByCurrentTime(Integer.parseInt(RandomUtil.getRandom(1, 12)));
                Blog blog = Blog.builder().title(title).summary(summary[0]).content(content).readTimes(Integer.parseInt(readTimes)).kinds(kinds).author(author).updateTime(updateTime).build();
//                blogRepository.save(blog);
            }
        }
    }

    @Test
    public void insertJuejin() throws Exception {
        List<String> head = Arrays.asList("recommended");
        List<String> tail = Arrays.asList("?sort=popular", "?sort=newest", "?sort=three_days_hottest");
        String url = "https://juejin.im/welcome/";
        List<String> authors = blogRepository.getAllAuthorNative();
        String random = RandomUtil.getRandom(0, authors.size() - 1);
        Document document = Jsoup.connect(url + head.get(0) + tail.get(0)).get();
        Thread.sleep(5000);
        List<String> articleUrls = document.getElementsByClass("title-row").stream().map(o -> o.getElementsByTag("a").get(0).attr("href")).collect(Collectors.toList());
        for (int j = 0; j < articleUrls.size(); j++) {
            Document doc = Jsoup.connect(articleUrls.get(j)).get();
            String title = doc.getElementsByClass("article-title").get(0).html();
            Map<String, Object> totalMap = blogRepository.countArticleByTitleNative(title);
            if ((int) totalMap.get("total") == 0) {
                String readTimes = RandomUtil.getRandom(1, 1000);
                String content = doc.getElementsByClass("article-content").get(0).html();
                String author = authors.get(Integer.parseInt(random));
                String kinds = labels.get(Integer.parseInt(RandomUtil.getRandom(0, labels.size() - 1)));
                Date updateTime = DateUtil.getBeforeByCurrentTime(Integer.parseInt(RandomUtil.getRandom(1, 23)));
                String summary = content.substring(0, content.length() / 4);
                Blog blog = Blog.builder().title(title).content(content).summary(summary).readTimes(Integer.parseInt(readTimes)).kinds(kinds).author(author).updateTime(updateTime).build();
//                blogRepository.save(blog);
            }
        }
    }

    @Test
    public void theftTouTiao() throws Exception {
        List<String> authors = blogRepository.getAllAuthorNative();
        String random = RandomUtil.getRandom(0, authors.size() - 1);
//        String html = FileUtil.readToString("D:\\simple-blog\\今日头条.html");
//        Document doc = Jsoup.parse(html);
//        Document doc = Jsoup.connect("https://www.toutiao.com").userAgent("Mozilla/5.0 (Windows NT 5.1; zh-CN) AppleWebKit/535.12 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/535.12").get();
        WebClient webClient = new WebClient(BrowserVersion.CHROME);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setTimeout(10000);
        HtmlPage rootPage = null;
        Document doc;
        try {
            rootPage = webClient.getPage("https://www.toutiao.com/ch/news_hot/");
            webClient.waitForBackgroundJavaScript(10000);
            String htmlString = rootPage.asXml();
            doc = Jsoup.parse(htmlString);
        } finally {
            webClient.close();
        }
        List<String> articleUrls = doc.getElementsByClass("title-box").stream().map(o -> o.getElementsByTag("a").get(0).attr("href")).collect(Collectors.toList());
        for (String articleUrl : articleUrls) {
            Document articleDoc;
            try {
                articleDoc = HttpUtil.getHtmlPageResponseAsDocument(articleUrl);
            } catch (Exception e) {
                log.error("访问头条失败： " + e.getMessage());
                continue;
            }
            String articleInfoHtml = articleDoc.body().getElementsByTag("script").get(3).html();
            JSONObject articleObject = JSON.parseObject("{"
                    + articleInfoHtml.substring(33, articleInfoHtml.length() - 12)
                    .replace(".slice(6, -6).replace(/<br \\/>/ig, '')", "")
                    .replace(".slice(6, -6)", "").replace("\\", "\\\\")
                    .replace("\\\\\"", "\\\"") + "}");
            String title = StringEscapeUtils.unescapeHtml4(articleObject.getJSONObject("articleInfo").getString("title")).replace("\\\\", "\\");
            Map<String, Object> totalMap = blogRepository.countArticleByTitleNative(title);
            if (totalMap.get("total").equals(new BigInteger("0"))) {
                String author = authors.get(Integer.parseInt(random));
                String readTimes = RandomUtil.getRandom(1, 1000);
                String kinds = labels.get(Integer.parseInt(RandomUtil.getRandom(0, labels.size() - 1)));
                Date updateTime = DateUtil.getBeforeByCurrentTime(Integer.parseInt(RandomUtil.getRandom(1, 23)));
                String content = StringEscapeUtils.unescapeEcmaScript(StringEscapeUtils.unescapeHtml4(articleInfoHtml.substring(articleInfoHtml.indexOf("content") + 10, articleInfoHtml.lastIndexOf(".slice(6, -6),") - 1))).replaceAll("&gt;", ">").replaceAll("&lt;", "<");
                String summary = StringEscapeUtils.unescapeHtml4(articleObject.getJSONObject("shareInfo").getString("abstract").replace("\\\\", "\\")).replaceAll("&gt;", ">").replaceAll("&lt;", "<");
            }
        }
    }
}
