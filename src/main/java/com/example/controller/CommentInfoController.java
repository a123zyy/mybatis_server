package com.example.controller;

import com.example.bean.CommentInfo;

import com.example.service.CommentInfoService;
import com.example.until.ErroMsg;
import com.example.until.GlobalnumInfo;
import com.example.until.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RequestMapping("/web/CommentInfo")
@RestController
@Api(value = "/CommentInfoController", tags = {"评论"})
@Slf4j
public class CommentInfoController {
    @Autowired
    public CommentInfoService commentInfoService;


    /**
     * 回复评论
     * @param postId
     * @param commentContent
     * @param parentId
     * @return Result
     * */
    @RequestMapping(value = "/CommentInfo",method = RequestMethod.GET)
    public Result addCommentInfo(int postId, String commentContent, int parentId, HttpServletRequest httpServletRequest){
        if (null == httpServletRequest.getHeader("uid")){
            return Result.error(ErroMsg.PRIMARY_ERROR);
        }
        return Result.success( commentInfoService.insertSelective(postId,commentContent,parentId,Integer.parseInt(httpServletRequest.getHeader("uid"))));
    }

    /**
     * 修改评论
     * @param commentID
     * @return Result
     * */
    @RequestMapping(value = "/CommentInfo/{commentID}",method = RequestMethod.PATCH)
    public Result delCommentInfo(@PathVariable int commentID){
        if (StringUtils.isEmpty(commentID)){
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        CommentInfo commentInfo = commentInfoService.selectByPrimaryKey(commentID);
        commentInfo.setStatus(GlobalnumInfo.NO_ASABLE.Key);
        //updateByPrimaryKeySelective会对字段进行判断再更新(如果为Null就忽略更新)，如果你只想更新某一字段，可以用这个方法。
        //updateByPrimaryKey对你注入的字段全部更新
        return Result.success(commentInfoService.updateByPrimaryKeySelective(commentInfo));
    }







}
