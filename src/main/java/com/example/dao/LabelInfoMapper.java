package com.example.dao;

import com.example.bean.LabelInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LabelInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LabelInfo record);

    int insertSelective(LabelInfo record);

    LabelInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LabelInfo record);

    int updateByPrimaryKey(LabelInfo record);

    int findAllByStatus(int status);
}