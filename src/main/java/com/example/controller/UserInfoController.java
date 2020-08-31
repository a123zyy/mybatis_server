package com.example.controller;
import com.example.bean.CommentInfo;
import com.example.bean.HobbyInfo;
import com.example.bean.LabelInfo;
import com.example.bean.PostInfo;
import com.example.bean.UserInfo;
import com.example.pojo.CommentInfoDto;
import com.example.pojo.HobbyInfoDto;
import com.example.pojo.PostInfoDto;
import com.example.pojo.UserInfoDto;
import com.example.service.CommentInfoService;
import com.example.service.HobbyInfoService;
import com.example.service.LabelInfoService;
import com.example.service.PostInfoService;
import com.example.service.UserInfoService;
import com.example.service.serviceImpl.UserInfoServiceImpl;
import com.example.until.ErroMsg;
import com.example.until.GlobalUntil;
import com.example.until.GlobalnumInfo;
import com.example.until.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/web/user")
public class UserInfoController {
    @Autowired
    public UserInfoService userInfoService;
    @Autowired
    public HobbyInfoService hobbyInfoService;
    @Autowired
    public LabelInfoService labelInfoService;
    @Autowired
    public PostInfoService postInfoService;
    @Autowired
    public CommentInfoService commentInfoService;


    //返回首页信息
    @RequestMapping(value = "/homeList",method = RequestMethod.GET)
    public Result getHome(int userId){
        Map<String,Object> map = new HashMap<String, Object>();
        if (userId == GlobalnumInfo.NO_ASABLE.Key|| StringUtils.isEmpty(userId)){
            return Result.error(ErroMsg.REDIS_NULL_ERROR);
        }
        UserInfo userInfo =  userInfoService.selectByPrimaryKey(1);
        UserInfoDto userInfoDto = new UserInfoDto();
        BeanUtils.copyProperties(userInfo,userInfoDto);
        map.put("userInfo",userInfoDto);
       //根据userid查询爱好
       List<HobbyInfo> list = hobbyInfoService.findAllByUserId(1);
       map.put("hobbyInfo",GlobalUntil.gethobbyInfo(list));
       //根据userid 查询标签总数
       Integer labelCount = labelInfoService.countAllByStatus(GlobalnumInfo.IS_ASABLE.Key,1);
       map.put("labelCount",labelCount);
       //根据userid 查询帖子总数
        List<PostInfo> postInfos = postInfoService.findbyUserId(1);
        map.put("postCount",this.getcommentCount(postInfos));
        return Result.success(map);
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
    private Integer getcommentCount(List<PostInfo> postInfos){
         Integer commentCount = 0;
         if (!StringUtils.isEmpty(postInfos)){
            for (PostInfo postInfo:postInfos){
                Integer count = 0;
                count = commentInfoService.findByPostId(postInfo.getId());
                commentCount+=count;
            }
        }
         return commentCount;
    }


}
