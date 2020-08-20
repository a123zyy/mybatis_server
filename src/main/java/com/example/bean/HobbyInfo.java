package com.example.bean;

import java.io.Serializable;
import lombok.Data;

@Data
public class HobbyInfo implements Serializable {
    private Integer id;

    private Integer userId;

    private String hobby;

    /**
     * 1正常  0不可用
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}