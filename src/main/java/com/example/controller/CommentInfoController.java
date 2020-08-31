package com.example.controller;

import com.example.bean.CommentInfo;
import com.example.pojo.CommentInfoDto;
import com.example.service.CommentInfoService;
import com.example.until.ErroMsg;
import com.example.until.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/web/CommentInfo")
@RestController
@Api(value = "/CommentInfoController", tags = {"评论"})
@Slf4j
public class CommentInfoController {
    @Autowired
    public CommentInfoService commentInfoService;

    @RequestMapping(value = "/CommentInfo",method = RequestMethod.POST)
    public Result addCommentInfo(@RequestBody CommentInfoDto commentInfoDto){
      if (StringUtils.isEmpty(commentInfoDto)){
          return Result.error(ErroMsg.PARAMER_NULL_ERROR);
      }
      if (commentInfoDto.getCommentContent() == null ||commentInfoDto.getCommentContent() == ""){
          return Result.error(ErroMsg.PARAMER_NULL_ERROR);
      }
      if (commentInfoDto.getCommentContent().length() > 150){
          return Result.error(ErroMsg.PARAMER_LENGTH_ERROR);
      }
        CommentInfo commentInfo =new CommentInfo();
        BeanUtils.copyProperties(commentInfoDto,commentInfo);
        return Result.success( commentInfoService.insertSelective(commentInfo));
    }


}
