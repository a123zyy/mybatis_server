package com.example.demo;

import com.example.bean.HobbyInfo;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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
        String name = "爱好,好好,乌拉,";
        System.out.println(name.substring(0,name.length()-1));

    }

    public String getUUID() {
        String s = UUID.randomUUID().toString();
        return s.replace("-", "");
    }
    public String gethobbyInfo(String hobbyInfo) {


        return hobbyInfo;

    }

//    String s = UUID.randomUUID().toString();
//32         // 去掉"-"符号
//        33         return s.replace("-", "");
//

}
