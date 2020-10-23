package com.example.bean;

import java.io.Serializable;
import lombok.Data;

@Data
public class CommentInfo implements Serializable {
    private Integer id;

    /**
     * 发评论id
     */
    private Integer userId;

    /**
     * 父uid
     */
    private Integer parentUid;

    /**
     * 父commentid
     */
    private Integer parentId;

    private String commentContent;

    /**
     * 帖子id
     */
    private Integer postId;

    /**
     * 评论者name
     */
    private String commentName;

    /**
     * 评论者头像
     */
    private String commentHead;

    /**
     * 1正常 0不正常
     */
    private Integer status;

    private Long createTime;

    private static final long serialVersionUID = 1L;
}