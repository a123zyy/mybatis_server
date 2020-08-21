package com.example.controller;

import com.example.bean.LabelInfo;
import com.example.bean.PostInfo;
import com.example.pojo.PostInfoDto;
import com.example.service.LabelInfoService;
import com.example.service.PostInfoService;
import com.example.until.GlobalnumInfo;

import com.example.until.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/PostInfo")
@RestController
@Api(value = "/PostInfoController", tags = {"评论"})
@Slf4j
public class PostInfoController {
    @Autowired
    public PostInfoService postInfoService;
    @Autowired
    public LabelInfoService labelInfoService;
    @RequestMapping(value = "/PostList",method = RequestMethod.POST)
    public Result getPostList(int pageSize,int pageNum){
        PageHelper.startPage(pageSize,pageNum);
        List<PostInfo> postInfos = postInfoService.findAllByStatus(GlobalnumInfo.IS_ASABLE.Key);

        List<PostInfoDto> postInfoDtos = postInfos.stream().map(item->{
            PostInfoDto postInfoDto = new PostInfoDto();
            BeanUtils.copyProperties(item,postInfoDto);
            LabelInfo labelInfo = labelInfoService.selectByPrimaryKey(postInfoDto.getLabelId());
            if (!StringUtils.isEmpty(labelInfo)){
                postInfoDto.setLabelName(labelInfo.getLable());
            }
            return postInfoDto;
        }).collect(Collectors.toList());
        Collections.sort(postInfoDtos, new Comparator<PostInfoDto>() {
            @Override
            public int compare(PostInfoDto o1, PostInfoDto o2) {
                return o1.getCreateTime().compareTo(o2.getCreateTime());
            }
        });
        PageInfo<PostInfoDto> pageInfo = new PageInfo<>(postInfoDtos);
        return Result.success(pageInfo);
    }

}
