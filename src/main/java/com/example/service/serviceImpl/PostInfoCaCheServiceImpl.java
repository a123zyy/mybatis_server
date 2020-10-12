package com.example.service.serviceImpl;

import com.example.service.PostInfoCaCheService;
import com.example.until.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zyy
 */
@Service
public class PostInfoCaCheServiceImpl implements PostInfoCaCheService {

//    @Value("${redis.database}")
//    private String REDIS_DATABASE;
//
//    @Value("${redis.expire.common}")
//    private Long REDIS_EXPIRE;

    private String REDIS_BLOG_POST_COUNT_LIKE="blog:post:like_count";

    private String REDIS_BLOG_POST_UID_LIKE = "blog:post:uid_like";

    @Autowired
    private RedisTemplate redisTemplate;


    @Resource(name = "redisTemplate")
    private ZSetOperations<String, String> zSetOperations;


    @Override
    public Boolean getIsLike(int uid) {
        return zSetOperations.zCard(REDIS_BLOG_POST_UID_LIKE + uid) == 0 ? false : true;
    }

    @Override
    public int giveLike(int userid, int postid) {
        //点赞
        zSetOperations.add(REDIS_BLOG_POST_UID_LIKE + userid, userid + "", System.currentTimeMillis());
        //获取文章赞总量
        Integer gitcount  = (Integer) redisTemplate.opsForValue().get(REDIS_BLOG_POST_COUNT_LIKE+postid)+1;
        //总量+1
        redisTemplate.opsForValue().set(REDIS_BLOG_POST_COUNT_LIKE+postid, gitcount);
        return gitcount;
    }

    @Override
    public int unGiveLike(int userid, int postid) {
        //取消点赞
        zSetOperations.remove(REDIS_BLOG_POST_UID_LIKE + userid, userid);
        Integer gitcount = (Integer) redisTemplate.opsForValue().get(REDIS_BLOG_POST_COUNT_LIKE+postid) -1;
        redisTemplate.opsForValue().set(REDIS_BLOG_POST_COUNT_LIKE+postid, gitcount);
        return gitcount;
    }
}
