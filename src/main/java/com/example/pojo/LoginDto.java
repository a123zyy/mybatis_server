package com.example.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "登录注册类")
public class LoginDto {
    public String username;
    public String password;
    @ApiModelProperty(value = "手机号,用于验证码")
    public Integer phoneNum;
    @ApiModelProperty(value = "1 账号密码 2验证码 2第三方登录")
    public int loginType;
}
