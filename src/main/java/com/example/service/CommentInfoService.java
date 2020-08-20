package com.example.service;

import com.example.bean.CommentInfo;

import java.util.List;

public interface CommentInfoService {


    int deleteByPrimaryKey(Integer id);

    int insert(CommentInfo record);

    int insertSelective(CommentInfo record);

    CommentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentInfo record);

    int updateByPrimaryKey(CommentInfo record);

    int findAllByStatus(int status);

    List<CommentInfo> findAllByPostId(int postId);

    List<CommentInfo> findAllByParentId(int parentId);


}



