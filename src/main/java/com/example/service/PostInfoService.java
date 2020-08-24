package com.example.service;

import com.example.bean.CommentInfo;
import com.example.bean.PostInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostInfoService {


    int deleteByPrimaryKey(Integer id);

    int insert(PostInfo record);

    int insertSelective(PostInfo record);

    PostInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostInfo record);

    int updateByPrimaryKey(PostInfo record);

    List<PostInfo> findbyUserId(int userId);

    List<PostInfo> findAllByUserId(int userId);

    List<PostInfo> findAllByStatus(int status);

    List<PostInfo> finAll();

    List<PostInfo> findAllByStatusAndLabelId(@Param("status") int status, @Param("labelId") int labelId);




}


