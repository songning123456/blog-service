package com.simple.blog.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author songning
 * @create 2019/7/24 13:31
 */
public class DateUtil {
    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 格式化日期
     *
     * @param date
     * @return "yyyy-MM-dd HH:mm:ss"的字符串
     */
    public static String dateFormat(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 字符串转日期
     *
     * @param dateString
     * @param dateFormat
     * @return
     */
    public static Date toDate(String dateString, String dateFormat) {
        Date date = null;
        try {
            date = new SimpleDateFormat(dateFormat).parse(dateString);
        } catch (Exception e) {
            return null;
        }
        return date;
    }

    /**
     * yyyy-MM-dd HH:mm:ss 转成 TimeStamp
     *
     * @param dateString
     * @return
     */
    public static Timestamp toTimeStamp(String dateString) {
        Timestamp timestamp = Timestamp.valueOf(dateString);
        return timestamp;
    }
}
