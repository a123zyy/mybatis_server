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

    Integer findAllByStatus(int status);

    List<CommentInfo> findAllByPostId(int postId);

    List<CommentInfo> findAllByParentId(int parentId);
}