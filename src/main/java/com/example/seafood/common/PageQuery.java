package com.example.seafood.common;

import lombok.Data;

@Data
public class PageQuery {
    private Integer pageSize;
    private Integer pageNum;
    private String queryKey;
}
