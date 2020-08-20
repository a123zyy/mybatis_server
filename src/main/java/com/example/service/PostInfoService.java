package com.example.service;

import com.example.bean.PostInfo;

import java.util.List;

public interface PostInfoService {


    int deleteByPrimaryKey(Integer id);

    int insert(PostInfo record);

    int insertSelective(PostInfo record);

    PostInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostInfo record);

    int updateByPrimaryKey(PostInfo record);

    int findAllByStatus(int status);

    PostInfo findAllByUserId(int userId);

}


