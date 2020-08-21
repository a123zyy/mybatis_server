package com.example.service.serviceImpl;

import com.example.bean.LabelInfo;
import com.example.dao.LabelInfoMapper;
import com.example.service.LabelInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LabelInfoServiceImpl implements LabelInfoService {

    @Resource
    private LabelInfoMapper labelInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return labelInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(LabelInfo record) {
        return labelInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(LabelInfo record) {
        return labelInfoMapper.insertSelective(record);
    }

    @Override
    public LabelInfo selectByPrimaryKey(Integer id) {
        return labelInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(LabelInfo record) {
        return labelInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(LabelInfo record) {
        return labelInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int countAllByStatus(int status,int userId) {
        return labelInfoMapper.findAllByStatus(status,userId);
    }

    @Override
    public List<LabelInfo> findAllByLabel(int status) {
        return labelInfoMapper.findAllByLabel(status);
    }

}

