package com.example.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class LoginInfo implements Serializable {
    private Integer id;

    private String username;

    private String password;

    /**
     * 1 密码验证码  2手机号登录   3第三方登录
     */
    private Integer type;

    private Date lasttime;

    /**
     * 1 正常 0注销
     */
    private String status;

    /**
     * 关联了用户表
     */
    private Integer userid;

    private Integer roleid;

    private Integer phoneNum;

    private static final long serialVersionUID = 1L;
}