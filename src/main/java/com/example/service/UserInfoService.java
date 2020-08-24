package com.example.service;

import com.example.bean.UserInfo;
import com.example.until.Result;

public interface UserInfoService{


    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    Result findAllBy(int pageSize, int pageNum);

    Integer countByNickname(String nickName);



}
