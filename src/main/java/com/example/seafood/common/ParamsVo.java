package com.example.seafood.common;

import lombok.Data;

@Data
public class ParamsVo {
    private Integer userId;
    private String password;
    private Integer pageNum;
    private Integer pageSize;
}
