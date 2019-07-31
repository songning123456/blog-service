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
}
