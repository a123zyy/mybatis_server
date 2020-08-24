package com.example.service.serviceImpl;

import com.example.until.Result;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.example.bean.HobbyInfo;
import com.example.dao.HobbyInfoMapper;
import com.example.service.HobbyInfoService;

import java.util.List;

@Service
public class HobbyInfoServiceImpl implements HobbyInfoService{

    @Resource
    private HobbyInfoMapper hobbyInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return hobbyInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(HobbyInfo record) {
        return hobbyInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(HobbyInfo record) {
        return hobbyInfoMapper.insertSelective(record);
    }

    @Override
    public HobbyInfo selectByPrimaryKey(Integer id) {
        return hobbyInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(HobbyInfo record) {
        return hobbyInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(HobbyInfo record) {
        return hobbyInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<HobbyInfo> findAllByUserId(int UserId) {
        return hobbyInfoMapper.findAllByUserId(UserId);
    }

    @Override
    public List<HobbyInfo> findAllByStatusAndUserid(int status, int userId) {
        return hobbyInfoMapper.findAllByStatusAndUserid(status, userId);
    }

    @Override
    public Result getAll() {
        return Result.success(hobbyInfoMapper.findAll());
    }

    @Override
    public Result hobbyDelete(int id, int status) {
        HobbyInfo hobbyInfo = hobbyInfoMapper.selectByPrimaryKey(id);
        hobbyInfo.setStatus(status);
        return Result.success(hobbyInfoMapper.updateByPrimaryKeySelective(hobbyInfo));
    }

}
