package com.example.service;

import com.example.bean.CommentInfo;
import com.example.until.Result;

import java.util.List;

public interface CommentInfoService {


    int deleteByPrimaryKey(Integer id);

    int insert(CommentInfo record);


    /**
     * 回复评论
     * @param postId
     * @param commentContent
     * @param userId
     * @param parentId
     * @return Result
     * */
    Result insertSelective(int postId,String commentContent,int userId,int parentId);

    CommentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentInfo record);

    int updateByPrimaryKey(CommentInfo record);

    int findByUserId(int status);

    List<CommentInfo> findAllByPostId(int postId);

    Integer findByPostId(int postId);

    CommentInfo findByParentId(int parentId);



}



