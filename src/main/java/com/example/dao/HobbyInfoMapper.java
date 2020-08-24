package com.example.dao;

import com.example.bean.HobbyInfo;
import com.example.bean.LabelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HobbyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HobbyInfo record);

    int insertSelective(HobbyInfo record);

    HobbyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HobbyInfo record);

    int updateByPrimaryKey(HobbyInfo record);

    List<HobbyInfo> findAllByUserId(@Param("UserId") int UserId);

    List<HobbyInfo> findAllByStatusAndUserid(@Param("status") int status, @Param("userId") int userId);

    List<HobbyInfo> findAll();


}