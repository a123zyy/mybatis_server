package com.example.dao;

import com.example.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    int findidByNickname(String nickname);

    UserInfo findById(int id);

    //查询全部
    List<UserInfo> findAll();

    //    Integer co(String nickName);
    int countByNickName(String nickName);
}