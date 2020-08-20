package com.example.pojo;

import com.example.bean.CommentInfo;
import lombok.Data;

import java.util.List;

@Data
public class CommentInfoDto extends CommentInfo {
    private List<CommentInfoDto> children;
}
