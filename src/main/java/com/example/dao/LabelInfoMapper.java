package com.example.dao;

import com.example.bean.LabelInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LabelInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LabelInfo record);

    int insertSelective(LabelInfo record);

    LabelInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LabelInfo record);

    int updateByPrimaryKey(LabelInfo record);

    int findAllByStatus(@Param("status") int status,@Param("userId") int userId);

    List<LabelInfo> findAllByLabel(int status);
}