package com.simple.blog.util;

/**
 * @author songning
 * @date 2019/10/23
 * description
 */
public class CssStyleUtil {

    public static String getBoldAndItalicFont(String text) {
        String cssFont = "<span style='font-style:italic;font-weight:bold'>";
        String cssEnd = "</span>";
        return cssFont + text + cssEnd;
    }
}
