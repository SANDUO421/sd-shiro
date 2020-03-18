package com.yulin.library;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.junit.Test;

public class IdTest {

    @Test
    public void idTest(){
        for (int i = 0; i < 100; i++) {
            long id = IdWorker.getId();
            System.out.println(id);
        }
    }

}
