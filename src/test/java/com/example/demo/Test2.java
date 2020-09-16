package com.example.demo;

import com.example.bean.PostInfo;
import com.example.service.CommentInfoService;
import com.example.until.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

@SpringBootTest
public class Test2 {
    @Autowired
    public CommentInfoService commentInfoService;
    @Autowired
    public RedisUtil redisUtil;
    @Test
    void contextLoads() {

        //commentInfoService.insertSelective(1,"我只是试试啦",2,0);
    }
    @Test
    void contextLoads1() {

        commentInfoService.deleteByPrimaryKey(5);
    }

    @Test
    void contextLoads2() {
        System.out.println(redisUtil.hget("user_id"+1,"user_id"));
    }

    @Test
    void contextLoads3() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

    }
}
