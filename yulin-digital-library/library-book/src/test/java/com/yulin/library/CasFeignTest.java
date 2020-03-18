package com.yulin.library;

import com.yulin.library.feign.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CasFeignTest {

    @Autowired
    private PayService payService;

    @Test
    public void test(){
//        ApiResponse byId = sysUserInfoService.get("Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJjdXJyZW50VXNlciI6eyJpZCI6MSwidXNlcm5hbWUiOm51bGwsImhlYWRQb3J0cmFpdHMiOiIxIiwicGhvbmUiOiIxIiwic2V4IjowfSwiY29kZSI6IjIwMCIsInVzZXJfbmFtZSI6IjEiLCJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNTgxMDA1MDY0LCJhdXRob3JpdGllcyI6WyJhbGwiLCJzeXNVc2VyOmNyZWF0ZSIsImJvb2tDb250ZW50OnZpZXciLCJzeXNVc2VyOnVwZGF0ZSIsInN5c1VzZXI6ZGVsZXRlIiwic3lzVXNlcjp2aWV3Il0sImp0aSI6ImJjMWRkN2QyLTIxN2QtNGFjMy1hYmQ2LTAzNGRmZmJkMzllMSIsImNsaWVudF9pZCI6InRlc3QifQ.ghPhRj0sv4uJqT7lydRFktEzy0-S3W8eR1_10kJ3HGOXGSQvo8SzB344k9wJa3uFU6MWEvTRwL_iTEVBGc6QislWY0Ns-W0NFRYQqz-TuwzTjF79RMMfQA_9ThmfU0QPo0Tyitdqk4TQbgR9AhNU4jwRRapNVMz7CMGLAGBVwheUuqGHiMCfOKTn1VRKYTJ1ohcxeisYkYjC1UNw2cjx8dLfA49Sp-1AC_KH022z0sRI80BXKaQxrrHjMH_hoUBV_vCQXy6Wl1T-owXCSoCTOo8Ooa9O1Qkt2VHfyu1vzDvkgXJ9Nrd0cj3qEgKk5ZmLlZxU-fwWnCwOehOTsuzMtA");
//        System.out.println(byId);
    }

}
