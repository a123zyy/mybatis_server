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
    Boolean getIsLike(int uid);

    /**
     * 用户给文章点赞并返回文章点赞总量
     * @param userid,postid
     * @return
     */
    int giveLike(int userid, int postid);

    /**
     * 用户给文章取消点赞并返回文章点赞总量
     * @param userid,postid
     * @return
     */
    int unGiveLike(int userid, int postid);


}
