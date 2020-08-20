package com.example.service;

import com.example.bean.HobbyInfo;

import java.util.List;

public interface HobbyInfoService{


    int deleteByPrimaryKey(Integer id);

    int insert(HobbyInfo record);

    int insertSelective(HobbyInfo record);

    HobbyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HobbyInfo record);

    int updateByPrimaryKey(HobbyInfo record);

    List<HobbyInfo> findAllByUserId(int UserId);



}
