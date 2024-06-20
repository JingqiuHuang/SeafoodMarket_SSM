package com.example.seafood.common;

import com.example.seafood.pojo.CartItem;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CartItemVo extends CartItem {
    private String provider;
    private String kind;
}
