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
import com.example.until.GlobalUntil;
import com.example.until.GlobalnumInfo;

import com.example.until.JwtTokenUtil;
import com.example.until.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.applet.Applet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @ApiOperation(value = "倒叙获得所有帖子", tags = {"倒叙获得所有帖子copy"}, notes = "分页必传")
    public Result getPostList(int pageSize, int pageNum,int labelId){
        List<PostInfo> postInfos=new ArrayList<>();
        if (labelId != GlobalnumInfo.NO_ASABLE.Key){
            PageHelper.startPage(pageNum, pageSize);
             postInfos=  postInfoService.findAllByStatusAndLabelId(GlobalnumInfo.IS_ASABLE.Key,labelId);
        } else {
            PageHelper.startPage(pageNum, pageSize);
            postInfos = postInfoService.findAllByStatus(GlobalnumInfo.IS_ASABLE.Key);

        }
        List<PostInfoDto> postInfoDtos = postInfos.stream().map(item->{
            PostInfoDto postInfoDto = new PostInfoDto();
            BeanUtils.copyProperties(item,postInfoDto);
            postInfoDto.setTime(GlobalUntil.dateFormat(item.getCreateTime()));
            LabelInfo labelInfo = labelInfoService.selectByPrimaryKey(postInfoDto.getLabelId());
            //返回评论数
            postInfoDto.setCommentCount(commentInfoService.findByPostId(postInfoDto.getId()));
            if (!StringUtils.isEmpty(labelInfo)) {
                postInfoDto.setLabelName(labelInfo.getLable());
            }
            return postInfoDto;
        }).collect(Collectors.toList());
        //根据userid 查询帖子总数
        PageInfo<PostInfoDto> pageInfo = new PageInfo<>(postInfoDtos);
        return Result.success(pageInfo);
    }


    @RequestMapping(value = "/List/Oneid", method = RequestMethod.GET)
    public Result PostInfoByOneid(HttpServletRequest httpServletRequest){
                //根据userid 查询标签总数 第一条帖子和评论
        int uid = 0;
        String token = httpServletRequest.getHeader("token");
        if (!StringUtils.isEmpty(token)){
            uid = jwtTokenUtil.getUseridFromToken(token);
        }
        return Result.success(postInfoService.findOnePostInfo(1,uid));
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

    /**
     * @param servletRequest
     * @param postid
     * @return Result
     *
     * */
    @RequestMapping(value = "/likes", method = RequestMethod.GET)
    @ApiOperation(value = "点赞", tags = {"倒叙获得所有帖子copy"}, notes = "参数必传")
    public Result giveLike(HttpServletRequest servletRequest,int postid){
        String token = servletRequest.getHeader("token");
        if (StringUtils.isEmpty(token)){
            Result.error(ErroMsg.USER__LOGIN_ERROR);
        }
        int uid = jwtTokenUtil.getUseridFromToken(token);

        return Result.success(postInfoService.giveLike(uid,postid));
    }
    /**
     * @param servletRequest
     * @param postid
     * @return Result
     *
     * */
    @PatchMapping(value = "/likes")
    @ApiOperation(value = "取消点赞", tags = {"倒叙获得所有帖子copy"}, notes = "参数必传")
    public Result unGiveLike(HttpServletRequest servletRequest,int postid){
        String token = servletRequest.getHeader("token");
        if (StringUtils.isEmpty(token)){
            Result.error(ErroMsg.USER__LOGIN_ERROR);
        }
        int uid = jwtTokenUtil.getUseridFromToken(token);
        return Result.success(postInfoService.unGiveLike(uid,postid));
    }

    @GetMapping(value = "/postinfosBycreateTime")
    public Result getPostinfoBycreateTime(){
        List<PostInfoDto> postInfos = postInfoService.finAll().stream().map(item->{
            PostInfoDto postInfoDto =new PostInfoDto();
            BeanUtils.copyProperties(item,postInfoDto);
            //postInfoDto.setTime(GlobalUntil.year(item.getCreateTime()));
            postInfoDto.setYear(GlobalUntil.year(item.getCreateTime()));
            return postInfoDto;
        }).collect(Collectors.toList());
//        List<PostInfo> postInfos = postInfoService.finAll();
//        List<PostInfoDto> postInfoDtos = postInfos.stream().map(item->{
//
//        }).collect(Collectors.toList());


        Map<String, List<PostInfoDto>> postInfoDtoMap = postInfos.stream().collect(Collectors.groupingBy(PostInfoDto::getYear));


//        LinkedHashMap<String,List<PostInfoDto>> map = postInfoDtoMap.entrySet().stream()
//                .sorted(Map.Entry.comparingByKey()).collect(Collectors
//                .toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
//                        LinkedHashMap::new));
        return Result.success(postInfoDtoMap);
    }

    @GetMapping(value = "/getPostById")
    @ApiOperation(value = "根据文章id获取文章及评论", tags = {"根据文章id获取文章及评论"})
    public Result getPostById(int postId,HttpServletRequest httpServletRequest){
        int uid = 0;
        String token = httpServletRequest.getHeader("token");
        if (!StringUtils.isEmpty(token)){
            uid = jwtTokenUtil.getUseridFromToken(token);
        }
        PostInfoDto postInfoDto = postInfoService.findOnePostInfo(postId,uid);
        return Result.success(postInfoDto);
    }



}
