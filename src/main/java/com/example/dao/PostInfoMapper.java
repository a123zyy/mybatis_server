package com.example.dao;

import com.example.bean.PostInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PostInfo record);

    int insertSelective(PostInfo record);

    PostInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostInfo record);

    int updateByPrimaryKey(PostInfo record);

    int findAllByStatus(int status);

    PostInfo findAllByUserId(int userId);
}