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
import com.example.service.PostInfoCaCheService;
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
    private LabelInfoMapper labelInfoMapper;
    @Autowired
    private CommentInfoMapper commentInfoMapper;

    @Autowired
    private PostInfoCaCheService postInfoCaCheService;

    @Autowired
    private CommentInfoService commentInfoService;




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
    public PostInfoDto findOnePostInfo(int id,int uid) {
        PostInfo postInfo = postInfoMapper.selectByPrimaryKey(id);
        PostInfoDto postInfoDto =new PostInfoDto();
        BeanUtils.copyProperties(postInfo,postInfoDto);
        if (!StringUtils.isEmpty(postInfoDto)){
            //根据labelid查询labelname
            LabelInfo labelInfo = labelInfoMapper.selectByPrimaryKey(postInfoDto.getLabelId());
            postInfoDto.setLabelName(labelInfo.getLable());
            //根据postid查出所有的评论list
            postInfoDto.setCommentInfoDtos(commentInfoService.findAllByPostId(postInfoDto.getId()));
            postInfoDto.setLike(postInfoCaCheService.getIsLike(uid,id));
            postInfoDto.setCommentCount( commentInfoMapper.findByPostId(id));
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
        return Result.success(postInfoCaCheService.giveLike(userid,postid));
    }

    @Override
    public Result unGiveLike(int userid, int postid) {
        return Result.success(postInfoCaCheService.unGiveLike(userid,postid));
    }

}


