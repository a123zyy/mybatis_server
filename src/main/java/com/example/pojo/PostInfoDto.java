package com.example.pojo;

import com.example.bean.PostInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class PostInfoDto extends PostInfo {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CommentInfoDto> commentInfoDtos;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String labelName;
}
