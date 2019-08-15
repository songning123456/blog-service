package com.simple.blog;

import com.simple.blog.repository.BlogRepository;
import com.simple.blog.repository.LabelGroupRepository;
import com.simple.blog.repository.SystemConfigRepository;
import com.simple.blog.util.FileUtil;
import com.simple.blog.util.NameConvertUtil;
import com.simple.blog.util.RegularUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    private BlogRepository blogRepository;

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

}
