package com.example.pojo;

import com.example.bean.CommentInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data

public class CommentInfoDto extends CommentInfo {
    private Integer id;
    @JsonIgnore
    private Integer status;

    private String parentName;

    private List<CommentInfoDto> children;


}
