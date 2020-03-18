package com.yulin.library.repository;

import com.yulin.library.model.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void save(){
        Book book=new Book();
        book.setName("aaa");
        bookRepository.save(book);
    }

}
