package com.example.service.serviceImpl;

import com.example.bean.CommentInfo;
import com.example.dao.CommentInfoMapper;
import com.example.service.CommentInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommentInfoServiceImpl implements CommentInfoService {

    @Resource
    private CommentInfoMapper commentInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return commentInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CommentInfo record) {
        return commentInfoMapper.insert(record);
    }

    @Override
    public int insertSelective(CommentInfo record) {
        return commentInfoMapper.insertSelective(record);
    }

    @Override
    public CommentInfo selectByPrimaryKey(Integer id) {
        return commentInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CommentInfo record) {
        return commentInfoMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CommentInfo record) {
        return commentInfoMapper.updateByPrimaryKey(record);
    }

    @Override
    public int findAllByStatus(int status) {
        return commentInfoMapper.findAllByStatus(status);
    }

    @Override
    public List<CommentInfo> findAllByPostId(int postId) {
        return commentInfoMapper.findAllByPostId(postId);
    }

    @Override
    public List<CommentInfo> findAllByParentId(int parentId) {
      return commentInfoMapper.findAllByParentId(parentId);
    }


}



