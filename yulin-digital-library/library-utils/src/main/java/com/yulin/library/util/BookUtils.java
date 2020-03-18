package com.yulin.library.util;

import com.yulin.library.util.model.entity.BaseBookContent;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookUtils {

    /**
     * 正则表达式
     */
    private static Pattern pattern = Pattern.compile("(^\\s*第)(.{1,9})[章节卷集部篇回](.*)($\\s*)");

    public static List<BaseBookContent> shardByChapter(InputStream inputStream) throws IOException {
        List<BaseBookContent> result=new LinkedList<>();

        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
        // 读取的每行的内容
        String lineTxt = null;
        Long count = (long) 0;
        // 内容
        String newStr=null;
        // 章节名称
        String newChapterName = null;

        while ((lineTxt = bufferedReader.readLine()) != null) {
            BaseBookContent baseBookContent =new BaseBookContent();

            newStr=newStr+lineTxt;

            Matcher matcher = pattern.matcher(lineTxt);
            while (matcher.find()) {
                baseBookContent.setSortNumber(count);
                if((count++)==0){
                    newStr = matcher.group();
                    //章节去空
                    newChapterName = newStr.trim();
                    break;
                }
                baseBookContent.setTitle(newChapterName);
                baseBookContent.setContent(newStr.replaceAll("\\s+","\r\n"));
                result.add(baseBookContent);
                newStr = matcher.group();
                newChapterName = newStr.trim();
            }
        }
        // 添加最后一个
        BaseBookContent baseBookContent =new BaseBookContent();
        baseBookContent.setSortNumber(count);
        baseBookContent.setTitle(newChapterName);
        baseBookContent.setContent(newStr);
        result.add(baseBookContent);

        bufferedReader.close();

        return result;
    }

    public static List<BaseBookContent> shardByChapter(byte[] content) throws IOException {
        return shardByChapter(new ByteArrayInputStream(content));
    }

    public static List<BaseBookContent> shardByChapter(File file) throws IOException {
        return shardByChapter(new FileInputStream(file));
    }

}
