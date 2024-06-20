package com.example.seafood.common;

import lombok.Data;

@Data
public class SeaFoodPageQuery extends PageQuery {
    private String queryKind;
    private String maxPrice;
    private String minPrice;
}
