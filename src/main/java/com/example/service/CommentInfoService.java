package com.example.service;

import com.example.bean.CommentInfo;
import com.example.pojo.CommentInfoDto;
import com.example.until.Result;

import java.util.List;

public interface CommentInfoService {


    int deleteByPrimaryKey(Integer id);

    int insert(CommentInfo record);


    /**
     * 回复评论
     * @param postId
     * @param commentContent
     * @param parentId
     * @return int
     * */
    Result insertSelective(int postId,String commentContent,int parentId,int parentUid,int userId);

    CommentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentInfo record);

    int updateByPrimaryKey(CommentInfo record);

    int findByUserId(int status);

    List<CommentInfoDto> findAllByPostId(int postId);

    Integer findByPostId(int postId);

    CommentInfo findByParentId(int parentId);

    List<CommentInfo> finAll();



}



