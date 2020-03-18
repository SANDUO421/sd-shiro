package com.yulin.library;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yulin.library.model.entity.BookContent;
import com.yulin.library.model.entity.Question;
import com.yulin.library.model.entity.UserComment;
import org.junit.Test;

public class ObjectMapperTest {

    @Test
    public void test1() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        Question question=new Question();
        String s = objectMapper.writeValueAsString(question);
        System.out.println(s);
    }

    @Test
    public void test2() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        BookContent bookContent=new BookContent();
        String s = objectMapper.writeValueAsString(bookContent);
        System.out.println(s);
    }

    @Test
    public void test3() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        UserComment userComment=new UserComment();
        String s = objectMapper.writeValueAsString(userComment);
        System.out.println(s);
    }

}
