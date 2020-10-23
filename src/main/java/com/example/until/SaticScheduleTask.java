package com.example.until;

import com.example.bean.PostInfo;
import com.example.dao.PostInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
@Slf4j
public class SaticScheduleTask {
    @Autowired
    private PostInfoMapper postInfoMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    private String REDIS_BLOG_POST_COUNT_LIKE="blog:post:like_count";


    @Scheduled(cron = "0 0/1 0 * * ?")
    //每两秒执行一次
    public void redisDataToMySQL() {
        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        //java8 localDateTime
        String st = dateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.of("Asia/Shanghai")));
        log.info("time:{}，开始执行Redis数据持久化到MySQL任务",st);

        //1.更新文章总的点赞数
        List<PostInfo> postInfos =  postInfoMapper.findAll();
        postInfos.stream().map(item->{
            Object likeCount = redisTemplate.opsForValue().get(REDIS_BLOG_POST_COUNT_LIKE+item.getId());
            if (Objects.nonNull(likeCount)){
                item.setLikeCount(likeCount.toString());
            }
            postInfoMapper.updateByPrimaryKeySelective(item);
            return item;
        }).collect(Collectors.toList());
    }
}
