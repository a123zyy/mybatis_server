package com.example.pojo;

import com.example.bean.HobbyInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class HobbyInfoDto extends HobbyInfo {
    @JsonIgnore
    private Integer userId;
}
