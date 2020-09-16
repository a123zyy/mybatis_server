package com.example.service.serviceImpl;

import com.example.bean.CommentInfo;
import com.example.dao.CommentInfoMapper;
import com.example.dao.UserInfoMapper;
import com.example.pojo.CommentInfoDto;
import com.example.service.CommentInfoService;
import com.example.until.ErroMsg;
import com.example.until.GlobalnumInfo;
import com.example.until.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zyy
 */
@Service
public class CommentInfoServiceImpl implements CommentInfoService {

    @Resource
    private CommentInfoMapper commentInfoMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return commentInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CommentInfo record) {
        return commentInfoMapper.insert(record);
    }

    @Override
    public Result insertSelective(int postId, String commentContent, int parentId, int userId) {
        if (StringUtils.isEmpty(postId)){
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        if (StringUtils.isEmpty(commentContent)){
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        if (commentContent.length() > 150){
            return Result.error(ErroMsg.PARAMER_LENGTH_ERROR);
        }
        if (StringUtils.isEmpty(userId)){
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }

        CommentInfo commentInfo =new CommentInfo();
        commentInfo.setCommentHead(userInfoMapper.selectByPrimaryKey(userId).getAvatar());
        commentInfo.setCommentContent(commentContent);
        commentInfo.setUserId(userId);
        commentInfo.setPostId(postId);
        commentInfo.setParentId(parentId);
        commentInfo.setCreateTime(System.currentTimeMillis());
        return Result.success(commentInfoMapper.insertSelective(commentInfo));
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
    public int findByUserId(int userId) {
        return commentInfoMapper.findByUserId(userId);
    }

    @Override
    public List<CommentInfoDto> findAllByPostId(int postId) {
        List<CommentInfo> commentInfos = commentInfoMapper.findAllByPostId(postId);
        List<CommentInfoDto> commentInfoDtos1 = commentInfos.stream()
                .filter(commentInfoDto -> commentInfoDto.getParentId() == GlobalnumInfo.NO_ASABLE.Key)
                .map(item -> {
                    CommentInfoDto commentInfoDto = new CommentInfoDto();
                    BeanUtils.copyProperties(item, commentInfoDto);
                    return commentInfoDto;
                }).peek(commentInfoDto -> {
                    List<CommentInfoDto> children = commentInfos.stream()
                            .filter(x -> commentInfoDto.getId().equals(x.getParentId()))
                            .map(x -> {
                                // 避免循环引用 创建新对象
                                CommentInfoDto newInfo = new CommentInfoDto();
                                BeanUtils.copyProperties(x, newInfo);
                                return newInfo;
                            })
                            .collect(Collectors.toList());
                    commentInfoDto.setChildren(children);
                }).collect(Collectors.toList());
        return commentInfoDtos1;
    }

    @Override
    public Integer findByPostId(int postId) {
        return commentInfoMapper.findByPostId(postId);
    }

    @Override
    public CommentInfo findByParentId(int parentId) {
        return commentInfoMapper.findByParentId(parentId);
    }

    @Override
    public List<CommentInfo> finAll() {
        return commentInfoMapper.finAll();
    }


}



