package com.example.service;


import com.example.until.Result;

/**
 * @author zyy
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface PostInfoCaCheService {

    /**
     * 根据uid查看用户是否点赞过
     * @param uid
     * @return
     */
    Boolean getIsLike(int uid,int postId);

    /**
     * 用户给文章点赞并返回文章点赞总量
     * @param userId,postid
     * @return
     */
    int giveLike(int userId, int postId);

    /**
     * 用户给文章取消点赞并返回文章点赞总量
     * @param userId,postId
     * @return
     */
    int unGiveLike(int userId, int postId);


}
