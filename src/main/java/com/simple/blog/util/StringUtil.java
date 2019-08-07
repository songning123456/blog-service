package com.simple.blog.util;

/**
 * @Author songning
 * @create 2019/7/31 14:24
 */
public class StringUtil {

    /**
     * 基础字符串拼接
     *
     * @param strings
     * @return
     * @throws Exception
     */
    public static String getString(String... strings) {
        StringBuilder b = new StringBuilder();
        for (String string : strings) {
            b.append(string);
        }
        return b.toString();
    }

    /**
     * 按指定字符分割字符串; 并返回第n个
     *
     * @param string
     * @return
     */
    public static String splitString(String string, String character, Integer n) {
        String[] strings = string.split(character);
        if (n < strings.length) {
            return strings[n];
        } else {
            return strings[strings.length - 1];
        }
    }
}
