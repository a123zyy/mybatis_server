package com.example.pojo;

import com.example.bean.UserInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfoDto extends UserInfo {

    public String hobbyName;
}
