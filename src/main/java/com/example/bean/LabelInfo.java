package com.example.bean;

import java.io.Serializable;
import lombok.Data;

@Data
public class LabelInfo implements Serializable {
    private Integer id;

    private String lable;

    private Integer userId;

    /**
     * 1 可用  0 不可用
     */
    private Integer status;

    /**
     * 帖子的统计量
     */
    private Integer labelCount;

    private static final long serialVersionUID = 1L;
}