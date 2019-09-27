package com.simple.blog;

import com.simple.blog.entity.Blog;
import com.simple.blog.entity.EsBlog;
import com.simple.blog.repository.BlogRepository;
import com.simple.blog.repository.EsBlogRepository;
import com.simple.blog.repository.LabelGroupRepository;
import com.simple.blog.repository.SystemConfigRepository;
import com.simple.blog.util.*;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;
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
    private EsBlogRepository esBlogRepository;

    @Autowired
    private LabelGroupRepository labelGroupRepository;

    @Autowired
    private SystemConfigRepository systemConfigRepository;

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
        List<String> target = new ArrayList<>();
        List<Map<String, Object>> src = blogRepository.getAllAuthorAndTitle();
        src.forEach(item -> {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                if (stringBuilder.length() == 0) {
                    stringBuilder.append("{'").append(entry.getKey()).append("':'").append(entry.getValue()).append("',");
                } else {
                    stringBuilder.append("'").append(entry.getKey()).append("':'").append(entry.getValue()).append("'},");
                }
            }
            target.add(stringBuilder.toString());
        });
        String fileName = "D:\\haiyan-data/blogData.txt";
        FileUtil.appendDataToFile(target, fileName);
        long fileLength = FileUtil.getFileSize(new File(fileName));
        System.out.println(fileLength);
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
     * 盗窃文章
     *
     * @throws Exception
     */
    @Test
    public void theftArticle() throws Exception {
        String url = "https://tech.meituan.com/";
        List<String> labels = Arrays.asList("WebSocket", "Vuex", "Chrome", "jQuery", "正则表达式", "HTTP", "MySQL", "ECMAScript 6", "Git", "HTML", "设计模式", "代码规范", "图片资源", "Linux", "机器学习", "Android", "iOS", "Java", "JavaScript");
        String random = RandomUtil.getRandom(2, 18);
        // 爬虫美团
        Document document = Jsoup.connect(url + "/page/" + random + ".html").get();
        List<String> articleUrls = document.getElementsByClass("post-title").stream().map(o -> o.getElementsByTag("a").get(0).attr("href")).collect(Collectors.toList());
        for (int j = 0; j < articleUrls.size(); j++) {
            Document doc = Jsoup.connect(articleUrls.get(Integer.parseInt(random))).get();
            String title = doc.getElementsByClass("post-title").get(0).getElementsByTag("a").html();
            String readTimes = RandomUtil.getRandom(1, 1000);
            String content = doc.getElementsByClass("post-content").html();
            String author = "songning";
            String kinds = labels.get(Integer.parseInt(RandomUtil.getRandom(0, labels.size() - 1)));
            Date updateTime = DateUtil.getBeforeByCurrentTime(Integer.parseInt(RandomUtil.getRandom(1, 23)));
            final String[] summary = new String[1];
            doc.getElementsByClass("post-content").get(0).getElementsByClass("content").get(0).getElementsByTag("p").forEach(item -> {
                if (!StringUtils.isEmpty(item.html())) {
                    summary[0] = item.html();
                }
            });
            EsBlog esBlog = EsBlog.builder().title(title).content(content).summary(summary[0]).readTimes(Integer.parseInt(readTimes)).kinds(kinds).author(author).updateTime(updateTime).build();
            esBlogRepository.save(esBlog);
        }
    }

    @Test
    public void theftBlog() throws Exception {
        String url = "https://www.boke.la/wenzhang/";
        List<String> labels = Arrays.asList("WebSocket", "Vuex", "Chrome", "jQuery", "正则表达式", "HTTP", "MySQL", "ECMAScript 6", "Git", "HTML", "设计模式", "代码规范", "图片资源", "Linux", "机器学习", "Android", "iOS", "Java", "JavaScript");
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
                if (!StringUtils.isEmpty(item.html())) {
                    summary[0] = item.html();
                }
            });
            String content = doc.getElementsByClass("zhengwen").get(0).getElementsByTag("p").html();
            String author = "songning";
            Date updateTime = DateUtil.getBeforeByCurrentTime(Integer.parseInt(RandomUtil.getRandom(1, 12)));
            EsBlog esBlog = EsBlog.builder().title(title).summary(summary[0]).content(content).readTimes(Integer.parseInt(readTimes)).kinds(kinds).author(author).updateTime(updateTime).build();
            esBlogRepository.save(esBlog);
        }
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
    public void deleteAll() {
        esBlogRepository.deleteAll();
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
}
