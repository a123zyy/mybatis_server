package com.example.controller;
import com.example.bean.CommentInfo;
import com.example.bean.HobbyInfo;
import com.example.bean.PostInfo;
import com.example.bean.UserInfo;
import com.example.pojo.CommentInfoDto;
import com.example.pojo.UserInfoDto;
import com.example.service.CommentInfoService;
import com.example.service.HobbyInfoService;
import com.example.service.LabelInfoService;
import com.example.service.PostInfoService;
import com.example.service.UserInfoService;
import com.example.until.ErroMsg;
import com.example.until.Result;
import io.swagger.models.auth.In;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    public UserInfoService userInfoService;

    public HobbyInfoService hobbyInfoService;

    public LabelInfoService labelInfoService;

    public PostInfoService postInfoService;

    public CommentInfoService commentInfoService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result getAllParame(){
       return Result.success( userInfoService.selectByPrimaryKey(2));
    }

    //返回首页信息
    public Result getHome(int userId){
        if (userId == 0 || StringUtils.isEmpty(userId)){
            return Result.error(ErroMsg.REDIS_NULL_ERROR);
        }

       UserInfo userInfo =  userInfoService.selectByPrimaryKey(1);
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(userInfo,userInfoDto);

       List<HobbyInfo> list = hobbyInfoService.findAllByUserId(1);
       Integer labelCount = labelInfoService.countAllByStatus(1);
       //帖子总数
       Integer postCount = postInfoService.findAllByStatus(1);
        //评论总数
       Integer commentCount =  commentInfoService.findAllByStatus(1);
       //第一条帖子和评论
        PostInfo postInfo = postInfoService.findAllByUserId(1);
//        if (!StringUtils.isEmpty(postInfo)){
//          List<CommentInfo> commentInfos = commentInfoService.findAllByPostId(postInfo.getId());
//          if (StringUtils.isEmpty(commentInfos) && commentInfos.size()>0){
//            List<CommentInfoDto> commentInfoDtos = commentInfos.stream().map(item->{
//              CommentInfoDto commentInfoDto = new CommentInfoDto();
//              BeanUtils.copyProperties(item,commentInfoDto);
//                if (commentInfoDto.getParentId()!=0){
//
//                }
//            }).collect(Collectors.toList());
//          }
//        }
        return Result.success("");
    }

    public List<CommentInfo> getChildren(int parentId){
        List<CommentInfo> commentInfos = new ArrayList<>();
        return commentInfos;
    }

}
