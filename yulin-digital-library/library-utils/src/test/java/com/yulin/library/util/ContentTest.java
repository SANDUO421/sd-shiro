package com.yulin.library.util;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentTest {

    @Test
    public void test(){
        String content="a   b";

        String s = content.replaceAll("\\s+", "\r\n");
        System.out.println(s);


        Pattern p = Pattern.compile("\\s*");
        Matcher m = p.matcher(content);
        String strNoBlank = m.replaceAll(" ");
        System.out.println(strNoBlank);
        System.out.println(content);
    }

}
