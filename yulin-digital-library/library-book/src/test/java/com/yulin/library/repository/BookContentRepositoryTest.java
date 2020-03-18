package com.yulin.library.repository;

import com.yulin.library.model.entity.BookContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BookContentRepositoryTest {

    @Autowired
    private BookContentRepository bookContentRepository;

    @Test
    public void test(){
        BookContent maxSortNumber = bookContentRepository.findFirstByBookIdAndSortNumberIsLessThanEqualOrderBySortNumberDesc("5e4604a6f3b3de76768ba89a", 3L);
        System.out.println(maxSortNumber);
    }

}
