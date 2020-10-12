package com.example.demo;

import com.example.bean.PostInfo;
import com.example.service.CommentInfoService;
import com.example.until.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
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
import java.util.LinkedList;
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
        //System.out.println(114/6);
//        String a ="cbbd";
//        System.out.println("a".hashCode());
        setString(34);

        //System.out.println( ispramary("remoom"));

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
       ArrayList<String>[] list =(ArrayList<String>[])new ArrayList[10];

        int newnumber=0;
        do
        {
            newnumber=newnumber*10+num%10;
            num/=10;
        }while(num>0);
        return newnumber;
    }
    //判断回文
    public boolean ispramary(String s){
        int low = 0;
        int high = s.length()-1;
        while (low<high){
            if (s.charAt(low)!= s.charAt(high)){
                return false;
            }
            low++;
            high--;

        }
        return true;
    }
    //判断回文数
    public static boolean isPalindrome(String s){
        String s1 =filter(s);
        String s2 =reverse(s1);
        return s2.equals(s1);
    }
    public  static  String filter(String s){
        StringBuilder stringBuilder =new StringBuilder();
        for (int i =0;i<s.length();i++){
            if (Character.isLetterOrDigit(s.charAt(i))){
                stringBuilder.append(s.charAt(i));
            }
        }
        return stringBuilder.toString();
    }
    public static String reverse(String s){
        StringBuilder stringBuilder =new StringBuilder(s);
        stringBuilder.reverse();
        return stringBuilder.toString();
    }
    //判断最长回文
    public String getparamer(String s){
        if (null == s){return s;}
        if (s.length() == 1){return s;}
        int low = 0;
        int hight = s.length()-1;
        String heiwen ="";
        while (low<hight){
            if (low  == hight){
                low++;
                hight = s.length()-1;
            }
            if (s.charAt(low) == s.charAt(hight)){
                System.out.println(s.charAt(low));
                System.out.println(s.charAt(hight));
                heiwen+= s.charAt(low);
                low++;
                hight--;
            } else {
                hight--;
                heiwen ="";

            }

        }
        StringBuilder stringBuilder =new StringBuilder(heiwen);
        return heiwen+stringBuilder.reverse();
    }
        //动态规划算法
        public String longestPalindrome(String s) {
            int n = s.length();
            boolean[][] dp = new boolean[n][n];
            String ans = "";
            for (int l = 0; l < n; ++l) {
                for (int i = 0; i + l < n; ++i) {
                    int j = i + l;
                    if (l == 0) {
                        dp[i][j] = true;
                    } else if (l == 1) {
                        dp[i][j] = (s.charAt(i) == s.charAt(j));
                    } else {
                        dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                    }
                    if (dp[i][j] && l + 1 > ans.length()) {
                        ans = s.substring(i, i + l + 1);
                    }
                }
            }
            return ans;
        }
        public void setString(int aaa){
            try {
                int[] newarray = this.createArry();
                System.out.println("下表为"+aaa+"的值"+newarray[aaa]);
            } catch (ArrayIndexOutOfBoundsException a){
                System.out.println("数组下标越界");
            }

        }
        public int[] createArry(){
            BigInteger integer = new BigInteger("21345675432125632454656");
            int[] array = new int[100];
            for (int i = 0;i<array.length;i++){
                array[i] = (int) (Math.random()*100)+1;
            }
            return array;
        }




}
