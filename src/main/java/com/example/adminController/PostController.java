package com.example.adminController;

import com.example.bean.LabelInfo;
import com.example.bean.PostInfo;
import com.example.pojo.PostInfoDto;
import com.example.service.CommentInfoService;
import com.example.service.LabelInfoService;
import com.example.service.PostInfoService;
import com.example.until.ErroMsg;
import com.example.until.Result;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/admin/post/")
@RestController
public class PostController {
    @Autowired
    public PostInfoService postInfoService;
    @Autowired
    public CommentInfoService commentInfoService;
    @Autowired
    public LabelInfoService labelInfoService;

    @RequestMapping(value = "postList", method = RequestMethod.GET)
    public Result getALL(int pageSize, int PageNum) {
        PageHelper.startPage(PageNum, pageSize);
        List<PostInfo> postInfos = postInfoService.finAll();
        List<PostInfoDto> postInfoDtolist = postInfos.stream().map(item -> {
            PostInfoDto postInfoDto = new PostInfoDto();
            BeanUtils.copyProperties(item, postInfoDto);
            //返回评论数
            postInfoDto.setCommentCount(commentInfoService.findByPostId(postInfoDto.getId()));
            //返回标签名
            LabelInfo labelInfo = labelInfoService.selectByPrimaryKey(postInfoDto.getLabelId());
            if (!StringUtils.isEmpty(labelInfo)) {
                postInfoDto.setLabelName(labelInfo.getLable());
            }
            return postInfoDto;
        }).collect(Collectors.toList());
        return Result.success(postInfoDtolist);
    }

    @RequestMapping(value = "PostInfo", method = RequestMethod.POST)
    public Result insertPost(@RequestBody PostInfoDto postInfoDto) {
        if (StringUtils.isEmpty(postInfoDto)) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        if (StringUtils.isEmpty(postInfoDto.getPostContent())) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        if (StringUtils.isEmpty(postInfoDto.getPostName())) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        if (StringUtils.isEmpty(postInfoDto.getLabelId())) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        PostInfo postInfo = new PostInfo();
        BeanUtils.copyProperties(postInfoDto, postInfo);
        return Result.success(postInfoService.insertSelective(postInfo));
    }

    @RequestMapping(value = "PostInfo", method = RequestMethod.PUT)
    public Result updateStatus(int id, int status) {
        if (id == 0) {
            return Result.error(ErroMsg.PARAMER_NULL_ERROR);
        }
        PostInfo postInfo = postInfoService.selectByPrimaryKey(id);
        postInfo.setStatus(status);
        return Result.success(postInfoService.updateByPrimaryKeySelective(postInfo));
    }


}
