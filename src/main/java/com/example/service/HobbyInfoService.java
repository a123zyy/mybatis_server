package com.example.service;

import com.example.bean.HobbyInfo;
import com.example.bean.LabelInfo;
import com.example.until.Result;

import java.util.List;

public interface HobbyInfoService{


    int deleteByPrimaryKey(Integer id);

    int insert(HobbyInfo record);

    int insertSelective(HobbyInfo record);

    HobbyInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HobbyInfo record);

    int updateByPrimaryKey(HobbyInfo record);

    List<HobbyInfo> findAllByUserId(int UserId);

    List<HobbyInfo> findAllByStatusAndUserid(int status, int userId);

    Result getAll();

    Result hobbyDelete(int id, int status);





}
