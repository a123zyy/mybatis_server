package com.example.pojo;

import com.example.bean.PostInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.List;

@Data
public class PostInfoDto extends PostInfo {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CommentInfoDto> commentInfoDtos;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiParam(value = "标签名称")
    private String labelName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ApiParam(value = "评论总数")
    private int commentCount;
    @ApiParam(value = "评论总数")
    private String likeCount;

    @ApiParam(value = "是否评论")
    private boolean isLike;

    private String time;
}
