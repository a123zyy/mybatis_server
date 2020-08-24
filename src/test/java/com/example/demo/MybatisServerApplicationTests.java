package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.UUID;

@SpringBootTest
class MybatisServerApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(getRandomHan());
    }

    private final static int DATA = 0x9fa5 - 0x4e00 + 1;

    public char getRandomHan() {
        Random ran = new Random();
        return (char) (0x4e00 + ran.nextInt(DATA));
    }

    @Test
    void contextLoads11() {

        System.out.println(getUUID());
    }

    public String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.replace("-", "");
    }

//    String s = UUID.randomUUID().toString();
//32         // 去掉"-"符号
//        33         return s.replace("-", "");
//

}
