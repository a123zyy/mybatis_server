package com.example.bean;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class CommentInfo implements Serializable {
    private Integer id;

    /**
     * 发帖人id
     */
    private Integer userId;

    /**
     * 父id
     */
    private Integer parentId;

    private String commentContent;

    private Integer postId;

    private String commentName;

    private String commentHead;

    /**
     * 1正常 0不正常
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}