package com.example.demo;

import com.example.bean.PostInfo;
import com.example.service.CommentInfoService;
import com.example.until.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
        System.out.println(redisUtil.hget("user_id" + 1, "user_id"));
    }

    @Test
    void contextLoads3() {
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
    }

    @Test
    void contextLoads4() {
//        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//
//
//        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
        //this.getCalendar(2020, 8);

        System.out.println( reverse(654));

    }

    public double getSales(int sales, double ui) {
        return sales * ui;
    }

    public void getCalendar(int year, int mouth) {
        Calendar cal = Calendar.getInstance();
        int daynum = 0;
        int print = 0;
        if (year < 1800 || year > cal.get(Calendar.YEAR)) {
            System.out.println("年份输入错误");
            throw new Error();
        }
        if (mouth < 1 || mouth > 12) {
            System.out.println("月份输入错误");
            throw new Error();
        }
        if (mouth == 2) {
            if (year % 4 == 0) {
                daynum = 28;
            } else {
                daynum = 28;
            }
        } else if (mouth == 1 || mouth == 3 || mouth == 5 || mouth == 7 || mouth == 8 || mouth == 10 || mouth == 12) {
            daynum = 31;
        } else {
            daynum = 30;
        }
        cal.setTime(getTime(year, mouth));
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w == 0) w = 7;
        System.out.println("周日\t周一\t周二\t周三\t周四\t周五\t周六\t");
        for (int j = 1; j <= w; j++) {
            System.out.print("*\t");
            print++;
        }
        for (int i = 1; i <= daynum; i++) {
            if (print % 7 == 0) {
                System.out.print("\n");
            }
            System.out.print(i + "\t");
            print++;
        }
    }

    private Date getTime(int year, int mouth) {
        //java8 DateTimeFormatter yyyy-mm-dd
        String data = year + "-" + mouth + "-" + "01";
        if (mouth < 10) {
            data = year + "-" + "0" + mouth + "-" + "01";
        }
        System.out.println(data);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(data, formatter);
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        java.util.Date utilDate = java.util.Date.from(instant);
        return utilDate;
    }
    public static int reverse(int num){
        int newnumber=0;
        do
        {
            newnumber=newnumber*10+num%10;
            num/=10;
        }while(num>0);
        return newnumber;
    }
}
