package com.example.seafood.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_cart_item")
public class CartItem {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer seafoodId;
    private String name;
    private Integer count;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private Integer cartId;

}
