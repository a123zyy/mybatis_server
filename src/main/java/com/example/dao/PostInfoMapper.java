package com.example.dao;

import com.example.bean.PostInfo;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

@Mapper
public interface PostInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PostInfo record);

    int insertSelective(PostInfo record);

    PostInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostInfo record);

    int updateByPrimaryKey(PostInfo record);

    List<PostInfo> findAllByUserId(@Param("userId") int userId);

    List<PostInfo> findAllByStatus(int status);

    List<PostInfo> findAll();

    List<PostInfo> findAllByYear();

    List<PostInfo> findAllByStatusAndLabelId(@Param("status") int status, @Param("labelId") int labelId);
}