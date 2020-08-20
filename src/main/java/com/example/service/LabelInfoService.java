package com.example.service;

import com.example.bean.LabelInfo;

public interface LabelInfoService {


    int deleteByPrimaryKey(Integer id);

    int insert(LabelInfo record);

    int insertSelective(LabelInfo record);

    LabelInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LabelInfo record);

    int updateByPrimaryKey(LabelInfo record);

    int countAllByStatus(int status);

}

