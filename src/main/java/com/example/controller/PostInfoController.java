package com.example.controller;

import com.example.bean.CommentInfo;
import com.example.bean.LabelInfo;
import com.example.bean.PostInfo;
import com.example.pojo.CommentInfoDto;
import com.example.pojo.PostInfoDto;
import com.example.service.CommentInfoService;
import com.example.service.LabelInfoService;
import com.example.service.PostInfoService;
import com.example.until.ErroMsg;
import com.example.until.GlobalnumInfo;

import com.example.until.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/web/PostInfo")
@RestController
@Api(value = "/PostInfoController", tags = {"评论"})
@Slf4j
public class PostInfoController {
    @Autowired
    public PostInfoService postInfoService;
    @Autowired
    public LabelInfoService labelInfoService;
    @Autowired
    public CommentInfoService commentInfoService;

    @RequestMapping(value = "/List", method = RequestMethod.GET)
    @ApiOperation(value = "倒叙获得所有帖子", tags = {"倒叙获得所有帖子copy"}, notes = "分页必传")
    public Result getPostList(int pageSize, int pageNum){
        PageHelper.startPage(pageNum, pageSize);
        List<PostInfo> postInfos = postInfoService.findAllByStatus(GlobalnumInfo.IS_ASABLE.Key);
        List<PostInfoDto> postInfoDtos = postInfos.stream().map(item->{
            PostInfoDto postInfoDto = new PostInfoDto();
            BeanUtils.copyProperties(item,postInfoDto);
            LabelInfo labelInfo = labelInfoService.selectByPrimaryKey(postInfoDto.getLabelId());
            //返回评论数
            postInfoDto.setCommentCount(commentInfoService.findByPostId(postInfoDto.getId()));
            if (!StringUtils.isEmpty(labelInfo)) {
                postInfoDto.setLabelName(labelInfo.getLable());
            }
            List<CommentInfo> commentInfos = commentInfoService.findAllByPostId(postInfoDto.getId());
            if (!StringUtils.isEmpty(commentInfos) && commentInfos.size()>GlobalnumInfo.NO_ASABLE.Key){
                List<CommentInfoDto> commentInfoDtos = commentInfos.stream().map(item1->{
                    CommentInfoDto commentInfoDto = new CommentInfoDto();
                    BeanUtils.copyProperties(item1,commentInfoDto);
                    return commentInfoDto;
                }).collect(Collectors.toList());
                postInfoDto.setCommentInfoDtos(this.getChildren(commentInfoDtos));
            }
            return postInfoDto;
        }).collect(Collectors.toList());
        //根据userid 查询帖子总数
        PageInfo<PostInfoDto> pageInfo = new PageInfo<>(postInfoDtos);
        return Result.success(pageInfo);
    }
    @RequestMapping(value = "/List/Oneid", method = RequestMethod.GET)
    public Result PostInfoByOneid(){
                //根据userid 查询标签总数 第一条帖子和评论
        PostInfo postInfo = postInfoService.selectByPrimaryKey(1);
        PostInfoDto postInfoDto =new PostInfoDto();
        BeanUtils.copyProperties(postInfo,postInfoDto);
        if (!StringUtils.isEmpty(postInfoDto)){
            //根据labelid查询labelname
            LabelInfo labelInfo = labelInfoService.selectByPrimaryKey(postInfoDto.getLabelId());
            postInfoDto.setLabelName(labelInfo.getLable());
            //根据postid查出所有的评论list
            List<CommentInfo> commentInfos = commentInfoService.findAllByPostId(postInfoDto.getId());
            if (!StringUtils.isEmpty(commentInfos) && commentInfos.size()>GlobalnumInfo.NO_ASABLE.Key){
            List<CommentInfoDto> commentInfoDtos = commentInfos.stream().map(item->{
              CommentInfoDto commentInfoDto = new CommentInfoDto();
              BeanUtils.copyProperties(item,commentInfoDto);
                             return commentInfoDto;
            }).collect(Collectors.toList());

            postInfoDto.setCommentInfoDtos(this.getChildren(commentInfoDtos));
          }
        }
        return Result.success(postInfoDto);
    }

    @RequestMapping(value = "/List/{labelId}", method = RequestMethod.GET)
    @ApiOperation(value = "根据标签获得帖子", tags = {"倒叙获得所有帖子copy"}, notes = "分页必传")
    public Result postListByLabelId(@PathVariable Integer labelId) {
        if (StringUtils.isEmpty(labelId) || labelId == 0) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        List<PostInfo> postInfos = postInfoService.findAllByStatusAndLabelId(GlobalnumInfo.IS_ASABLE.Key, labelId);
        List<PostInfoDto> postInfoDtos = postInfos.stream().map(item -> {
            PostInfoDto postInfoDto = new PostInfoDto();
            BeanUtils.copyProperties(item, postInfoDto);
            LabelInfo labelInfo = labelInfoService.selectByPrimaryKey(postInfoDto.getLabelId());
            //返回评论数
            postInfoDto.setCommentCount(commentInfoService.findByPostId(postInfoDto.getId()));
            if (!StringUtils.isEmpty(labelInfo)){
                postInfoDto.setLabelName(labelInfo.getLable());
            }
            return postInfoDto;
        }).collect(Collectors.toList());
        PageInfo<PostInfoDto> pageInfo = new PageInfo<>(postInfoDtos);
        return Result.success(pageInfo);
    }

    public List<CommentInfoDto> getChildren(List<CommentInfoDto> commentInfoDtos){
        List<CommentInfoDto> dtoList = new ArrayList<>();
        for (CommentInfoDto commentInfoDto:commentInfoDtos){
            //父评论为0是第一条
            if (commentInfoDto.getParentId() == GlobalnumInfo.NO_ASABLE.Key){
                List<CommentInfoDto> commentInfoDtos1 = new ArrayList<>();
                for (int i = 0;i <commentInfoDtos.size();i++){
                    if(commentInfoDto.getId().equals(commentInfoDtos.get(i).getParentId())){
                        commentInfoDtos1.add(commentInfoDtos.get(i));
                    }
                }
                commentInfoDto.setChildren(commentInfoDtos1);
                dtoList.add(commentInfoDto);
            }
        }
        return  dtoList;
    }

}
