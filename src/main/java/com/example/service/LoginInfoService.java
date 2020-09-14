package com.example.service;

import com.example.bean.LoginInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LoginInfoService{


    int deleteByPrimaryKey(Integer id);

    int insert(LoginInfo record);

    int insertSelective(LoginInfo record);

    LoginInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginInfo record);

    int updateByPrimaryKey(LoginInfo record);

    List findByUsernameAndPassword(String username,String password);

    List seachByUsername(String username);


    boolean findByUsername(String username);

    LoginInfo selectByUserName(String username);
}
