package com.example.service.serviceImpl;


import com.example.bean.LoginInfo;
import com.example.bean.UserInfo;
import com.example.dao.LoginInfoMapper;
import com.example.service.LoginInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginInfoServiceImpl implements LoginInfoService {

    @Resource
    private LoginInfoMapper loginInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return loginInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(LoginInfo record) {
        return loginInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(LoginInfo record) {
        return loginInfoMapper.insertSelective(record);
    }

    @Override
    public LoginInfo selectByPrimaryKey(Integer id) {
        return loginInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(LoginInfo record) {
        return loginInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(LoginInfo record) {
        return loginInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<LoginInfo> findByUsernameAndPassword(String username, String password) {
        return loginInfoMapper.findByUsernameAndPassword(username,password);
    }

    @Override
    public boolean findByUsername(String username) {
        return loginInfoMapper.findByUsername(username) == null? false:true;
    }

    @Override
    public LoginInfo selectByUserName(String username) {
        return loginInfoMapper.selectByUserName(username);
    }

}
