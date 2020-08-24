package com.example.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.example.bean.HobbyInfo;
import lombok.Data;

@Data
public class HobbyInfoDto extends HobbyInfo {
    @JSONField(serialize = false)
    private Integer userId;
}
