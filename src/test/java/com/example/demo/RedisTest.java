package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTest {


    private String REDIS_BLOG_POST_COUNT_LIKE="blog:post:like_count";

    private String REDIS_BLOG_POST_UID_LIKE = "blog:post:uid_like";

    @Autowired
    private RedisTemplate redisTemplate;


    @Resource(name = "redisTemplate")
    private ZSetOperations<String, String> zSetOperations;


    @Test
    void contextLoads() {
        //
//        zSetOperations.add(REDIS_BLOG_POST_UID_LIKE + 3, 1+"", System.currentTimeMillis());
//
//
//        zSetOperations.remove(REDIS_BLOG_POST_UID_LIKE + 3, 1+"");
        long aa = zSetOperations.zCard(REDIS_BLOG_POST_UID_LIKE + 1);
        System.out.println(aa);



        //Object likeCount = redisTemplate.opsForValue().get(REDIS_BLOG_POST_COUNT_LIKE+1);


       // long aaa = zSetOperations.remove(REDIS_BLOG_POST_UID_LIKE + 3,3);

//        System.out.println(aa);
    }
}
