package com.simple.blog.util;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.List;

/**
 * @Author songning
 * @create 2019/7/26 13:37
 */
public class FileUtil {

    /**
     * 将数据追加写入到文件文件的末尾
     *
     * @param dataList
     * @param fileName
     */
    public static void appendDataToFile(List<String> dataList, String fileName) {
        OutputStreamWriter outputStreamWriter = null;
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(fileName);
            if (!file.getParentFile().isDirectory()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            fileOutputStream = new FileOutputStream(fileName, true);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            for (int i = 0; i < dataList.size(); i++) {
                outputStreamWriter.write(dataList.get(i));
                outputStreamWriter.write("\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStreamWriter != null) {
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static long getFileSize(File file) {
        FileChannel fileChannel = null;
        try {
            if (file.exists() && file.isFile()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileChannel = fileInputStream.getChannel();
                return fileChannel.size();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileChannel != null) {
                try {
                    fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }
}
