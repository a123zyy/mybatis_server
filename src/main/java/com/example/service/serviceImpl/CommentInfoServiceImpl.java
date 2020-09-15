package com.example.service.serviceImpl;

import com.example.bean.CommentInfo;
import com.example.dao.CommentInfoMapper;
import com.example.dao.UserInfoMapper;
import com.example.service.CommentInfoService;
import com.example.until.ErroMsg;
import com.example.until.Result;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentInfoServiceImpl implements CommentInfoService {

    @Resource
    private CommentInfoMapper commentInfoMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return commentInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CommentInfo record) {
        return commentInfoMapper.insert(record);
    }

    @Override
    public Result insertSelective(int postId,String commentContent,int parentId,int userId) {
        if (StringUtils.isEmpty(postId)){
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        if (StringUtils.isEmpty(commentContent)){
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        if (commentContent.length() > 150){
            return Result.error(ErroMsg.PARAMER_LENGTH_ERROR);
        }
        if (StringUtils.isEmpty(userId)){
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }

        CommentInfo commentInfo =new CommentInfo();
        commentInfo.setCommentHead(userInfoMapper.selectByPrimaryKey(userId).getAvatar());
        commentInfo.setCommentContent(commentContent);
        commentInfo.setUserId(userId);
        commentInfo.setPostId(postId);
        commentInfo.setParentId(parentId);
        commentInfo.setCreateTime(System.currentTimeMillis());
        return Result.success(commentInfoMapper.insertSelective(commentInfo));
    }

    @Override
    public CommentInfo selectByPrimaryKey(Integer id) {
        return commentInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CommentInfo record) {
        return commentInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CommentInfo record) {
        return commentInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int findByUserId(int userId) {
        return commentInfoMapper.findByUserId(userId);
    }

    @Override
    public List<CommentInfo> findAllByPostId(int postId) {
        return commentInfoMapper.findAllByPostId(postId);
    }

    @Override
    public Integer findByPostId(int postId) {
        return commentInfoMapper.findByPostId(postId);
    }

    @Override
    public CommentInfo findByParentId(int parentId) {
      return commentInfoMapper.findByParentId(parentId);
    }




}



