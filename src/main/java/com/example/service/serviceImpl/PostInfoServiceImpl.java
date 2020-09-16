package com.example.service.serviceImpl;


import com.example.bean.CommentInfo;
import com.example.bean.LabelInfo;
import com.example.bean.PostInfo;
import com.example.dao.CommentInfoMapper;
import com.example.dao.LabelInfoMapper;
import com.example.dao.PostInfoMapper;
import com.example.pojo.CommentInfoDto;
import com.example.pojo.PostInfoDto;
import com.example.service.CommentInfoService;
import com.example.service.PostInfoService;
import com.example.until.ErroMsg;
import com.example.until.GlobalnumInfo;
import com.example.until.RedisUtil;
import com.example.until.Result;
import lombok.Data;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;
@Slf4j
@Service
public class PostInfoServiceImpl implements PostInfoService {

    @Resource
    private PostInfoMapper postInfoMapper;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private LabelInfoMapper labelInfoMapper;
    @Autowired
    private CommentInfoMapper commentInfoMapper;

    @Autowired
    private CommentInfoService commentInfoService;

    public static final String GIVE_LIKE = "give_like";

    public static final String USER_ID = "user_id";


    @Resource(name = "redisTemplate")
    private ZSetOperations<String, String> zSetOperations;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return postInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PostInfo record) {
        return postInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(PostInfo record) {
        return postInfoMapper.insertSelective(record);
    }

    @Override
    public PostInfoDto findOnePostInfo(Integer id,String uid) {
        PostInfo postInfo = postInfoMapper.selectByPrimaryKey(1);
        PostInfoDto postInfoDto =new PostInfoDto();
        BeanUtils.copyProperties(postInfo,postInfoDto);
        if (!StringUtils.isEmpty(postInfoDto)){
            //根据labelid查询labelname
            LabelInfo labelInfo = labelInfoMapper.selectByPrimaryKey(postInfoDto.getLabelId());
            postInfoDto.setLabelName(labelInfo.getLable());
            //根据postid查出所有的评论list
            postInfoDto.setCommentInfoDtos(commentInfoService.findAllByPostId(postInfoDto.getId()));
            if (Objects.nonNull(uid)) {
                postInfoDto.setLike(zSetOperations.zCard(USER_ID + uid) == null ? false : true);
            }

        }
        return postInfoDto;
    }

    @Override
    public PostInfo selectByPrimaryKey(int id) {
        return postInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PostInfo record) {
        return postInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PostInfo record) {
        return postInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<PostInfo> findbyUserId(int userId) {
        return postInfoMapper.findAllByUserId(userId);
    }

    @Override
    public List<PostInfo> findAllByUserId(int userId) {
        return postInfoMapper.findAllByUserId(userId);
    }

    @Override
    public List<PostInfo> findAllByStatus(int status) {
        return postInfoMapper.findAllByStatus(status);
    }

    @Override
    public List<PostInfo> finAll() {
        return postInfoMapper.findAll();
    }

    @Override
    public List<PostInfo> findAllByStatusAndLabelId(int status, int labelId) {
        return postInfoMapper.findAllByStatusAndLabelId(status, labelId);
    }

    @Override
    public Result giveLike(int userid, int postid) {
        Integer gitcount =0;
        //判断是否点赞
        if (getParamer(userid,userid)){
           try {
               //点赞
               zSetOperations.add(USER_ID + userid, userid + "", System.currentTimeMillis());
               //总量加1
               gitcount  = (Integer) redisTemplate.opsForValue().get(GIVE_LIKE+postid);
               if (gitcount == null){
                   gitcount = 0;
               }
               redisTemplate.opsForValue().set(GIVE_LIKE+postid, gitcount+1);
           } catch (Exception e){
               return Result.error(ErroMsg.BIND_ERROR);
           }
        }
        return Result.success(gitcount);
    }

    @Override
    public Result unGiveLike(int userid, int postid) {
        Integer gitcount = 0;
        //判断是否点赞
        if (!getParamer(userid,postid)){
            try {
                //取消点赞
                zSetOperations.remove(USER_ID + userid, userid);
                //总量减1
                gitcount  = (Integer) redisTemplate.opsForValue().get(GIVE_LIKE+postid);
                redisTemplate.opsForValue().set(GIVE_LIKE+postid, gitcount-1);
            } catch (Exception e){
                log.info("redis遇见错误" + e);
                return Result.error(ErroMsg.BIND_ERROR);
            }
        }
        return Result.success(gitcount);
    }
    //遍历出所以评论
    public List<CommentInfoDto> getChildren(List<CommentInfoDto> commentInfoDtos){
        List<CommentInfoDto> dtoList = new ArrayList<>();
        for (CommentInfoDto commentInfoDto:commentInfoDtos){
            //父评论为0是第一条
            if (commentInfoDto.getParentId() == GlobalnumInfo.NO_ASABLE.Key){
                List<CommentInfoDto> commentInfoDtos1 = new ArrayList<>();
                for (int i = 0;i <commentInfoDtos.size();i++){
                    if(commentInfoDto.getId().equals(commentInfoDtos.get(i).getParentId())){
                        commentInfoDtos1.add(commentInfoDtos.get(i));
                    }
                }
                commentInfoDto.setChildren(commentInfoDtos1);
                dtoList.add(commentInfoDto);
            }
        }
        return  dtoList;
    }



    //校验参数
    public boolean getParamer(int userid, int postid){
        return redisTemplate.opsForHash().get(userid,postid) == null;
    }

}


