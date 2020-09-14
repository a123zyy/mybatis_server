package com.example.demo;

import com.example.controller.CommentInfoController;
import com.example.service.CommentInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test2 {
    @Autowired
    public CommentInfoService commentInfoService;
    @Test
    void contextLoads() {

        commentInfoService.insertSelective(1,"我只是试试啦",2,0);
    }
    @Test
    void contextLoads1() {

        commentInfoService.deleteByPrimaryKey(5);
    }
}
