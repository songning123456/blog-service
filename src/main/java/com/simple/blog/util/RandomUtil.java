package com.simple.blog.util;

import java.util.Random;

/**
 * @author songning
 * @date 2019/8/29
 * description 获取随机数
 */
public class RandomUtil {
    public static String getRandom(int min, int max) {
        Random random = new Random();
        return String.valueOf(random.nextInt(max) % (max - min + 1) + min);
    }
}
