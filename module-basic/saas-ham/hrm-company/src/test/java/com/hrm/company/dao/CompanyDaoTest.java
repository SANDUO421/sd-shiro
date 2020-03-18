package com.hrm.company.dao;

import com.hrm.domain.company.entity.Company;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 三多
 * @Time 2020/3/11
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CompanyDaoTest {
    @Autowired
    private CompanyDao companyDao;

    @BeforeEach
    void setUp() {
    }

    /**
     * 添加
     */
    @Test
    public void add(){
        Company entity = new Company();
         entity.setId("2");
         entity.setName("陕西电信");
         entity.setManagerId("123");
         entity.setBalance(12.5);
         entity.setCreateTime(new Date());
         entity.setState(2);
        companyDao.saveAndFlush(entity);
    }
    @Test
    public void findById(){
        Company company = companyDao.findById("1").get();
        System.out.println(company);
    }
}