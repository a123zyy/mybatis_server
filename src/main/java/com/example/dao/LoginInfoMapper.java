package com.example.dao;

import com.example.bean.LoginInfo;
import org.apache.ibatis.annotations.Mapper;import org.apache.ibatis.annotations.Param;import java.util.List;

@Mapper
public interface LoginInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoginInfo record);

    int insertSelective(LoginInfo record);

    LoginInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginInfo record);

    int updateByPrimaryKey(LoginInfo record);

    List findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    Integer findByUsername(String username);

    LoginInfo selectByUserName(String username);
}