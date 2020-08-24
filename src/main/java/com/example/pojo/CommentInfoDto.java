package com.example.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.bean.CommentInfo;
import lombok.Data;

import java.util.List;

@Data

public class CommentInfoDto extends CommentInfo {
    private Integer id;
    @JSONField(serialize=false)
    private Integer status;

    private List<CommentInfoDto> children;


}
