package com.example.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class PostInfo implements Serializable {
    private Integer id;

    /**
     * 帖子 内容富文本
     */
    private String postContent;

    /**
     * 帖子标题
     */
    private String postName;

    private Integer postType;

    private Date createTime;

    /**
     * 0 删除 1可用
     */
    private Integer status;

    private Integer userId;

    /**
     * 文章点赞总数
     */
    private String likeCount;

    private Integer labelId;

    private static final long serialVersionUID = 1L;
}