package com.example.service.serviceImpl;


import com.example.bean.CommentInfo;
import com.example.bean.PostInfo;
import com.example.dao.PostInfoMapper;
import com.example.service.PostInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PostInfoServiceImpl implements PostInfoService {

    @Resource
    private PostInfoMapper postInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return postInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PostInfo record) {
        return postInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(PostInfo record) {
        return postInfoMapper.insertSelective(record);
    }

    @Override
    public PostInfo selectByPrimaryKey(Integer id) {
        return postInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(PostInfo record) {
        return postInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(PostInfo record) {
        return postInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<PostInfo> findbyUserId(int userId) {
        return postInfoMapper.findAllByUserId(userId);
    }

    @Override
    public List<PostInfo> findAllByUserId(int userId) {
        return postInfoMapper.findAllByUserId(userId);
    }

    @Override
    public List<PostInfo> findAllByStatus(int status) {
        return postInfoMapper.findAllByStatus(status);
    }

    @Override
    public List<PostInfo> finAll() {
        return postInfoMapper.findAll();
    }

    @Override
    public List<PostInfo> findAllByStatusAndLabelId(int status, int labelId) {
        return postInfoMapper.findAllByStatusAndLabelId(status, labelId);
    }

}


