package com.example.service.serviceImpl;

import com.example.bean.HobbyInfo;
import com.example.bean.LabelInfo;
import com.example.bean.UserInfo;
import com.example.dao.UserInfoMapper;
import com.example.pojo.UserInfoDto;
import com.example.service.HobbyInfoService;
import com.example.service.LabelInfoService;
import com.example.service.UserInfoService;
import com.example.until.GlobalUntil;
import com.example.until.GlobalnumInfo;
import com.example.until.PageInfo;
import com.example.until.Result;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private HobbyInfoService hobbyInfoService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserInfo record) {
        return userInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(UserInfo record) {
        //存储完并返回id
        int userid = userInfoMapper.insertSelective(record);
        return userInfoMapper.findidByNickname(record.getNickname());
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo record) {
        return userInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserInfo record) {
        return userInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public Result findAllBy(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> list = userInfoMapper.findAll();
        List<UserInfoDto> list1 = list.stream().map(item -> {
            UserInfoDto userInfoDto = new UserInfoDto();
            BeanUtils.copyProperties(item, userInfoDto);
            //根据userid查询爱好
            List<HobbyInfo> hobbyInfos = hobbyInfoService.findAllByStatusAndUserid(GlobalnumInfo.IS_ASABLE.Key, userInfoDto.getId());
            userInfoDto.setHobbyName(GlobalUntil.gethobbyInfo(hobbyInfos));
            return userInfoDto;
        }).collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(list1);
        return Result.success(pageInfo);
    }

    @Override
    public Integer countByNickname(String nickName) {
        return userInfoMapper.countByNickName(nickName);
    }




}
