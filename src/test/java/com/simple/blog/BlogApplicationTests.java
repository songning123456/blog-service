package com.simple.blog;

import com.simple.blog.entity.Blog;
import com.simple.blog.entity.EsBlog;
import com.simple.blog.repository.BlogRepository;
import com.simple.blog.repository.EsBlogRepository;
import com.simple.blog.repository.LabelGroupRepository;
import com.simple.blog.repository.SystemConfigRepository;
import com.simple.blog.util.FileUtil;
import com.simple.blog.util.NameConvertUtil;
import com.simple.blog.util.RegularUtil;
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

import java.io.*;
import java.util.*;
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
        // 爬虫美团
        for (int i = 4; i < 8; i++) {
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
                String kinds = labels.get(j % labels.size());
                EsBlog esBlog = EsBlog.builder().title(title).content(content).summary(title + Math.random()).readTimes(readTimes).kinds(kinds).author(author).updateTime(new Date()).build();
                esBlogRepository.save(esBlog);
            }
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

}
