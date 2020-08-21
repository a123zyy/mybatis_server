package com.example.dao;

import com.example.bean.CommentInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentInfo record);

    int insertSelective(CommentInfo record);

    CommentInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentInfo record);

    int updateByPrimaryKey(CommentInfo record);

    Integer findByUserId(int userId);

    List<CommentInfo> findAllByPostId(int postId);

    CommentInfo findByParentId(int parentId);

    Integer findByPostId(int postId);

}