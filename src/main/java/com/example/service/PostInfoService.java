package com.example.service;

import com.example.bean.CommentInfo;
import com.example.bean.PostInfo;
import com.example.pojo.PostInfoDto;
import com.example.until.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostInfoService {


    int deleteByPrimaryKey(Integer id);

    int insert(PostInfo record);

    int insertSelective(PostInfo record);

    PostInfoDto findOnePostInfo(Integer id, String uid);

    PostInfo selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(PostInfo record);

    int updateByPrimaryKey(PostInfo record);

    List<PostInfo> findbyUserId(int userId);

    List<PostInfo> findAllByUserId(int userId);

    List<PostInfo> findAllByStatus(int status);

    List<PostInfo> finAll();

    List<PostInfo> findAllByStatusAndLabelId(@Param("status") int status, @Param("labelId") int labelId);

     Result giveLike(int userid,int postid);

     Result unGiveLike(int userid,int postid);



}


