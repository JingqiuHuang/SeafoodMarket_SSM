package com.example.seafood.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.seafood.common.CartItemVo;
import com.example.seafood.pojo.CartItem;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface CartMapper extends BaseMapper<CartItem> {

    void cleanCart(@Param("cartId") Integer cartId);

    void deleteCartItem(@Param("id") Integer id);

    void updateCountAndPrice(@Param("count") Integer count, @Param("totalPrice") BigDecimal totalPrice,
                             @Param("seafoodId") Integer seafoodId, @Param("cartId") Integer cartId);

    void insertCart(@Param("userId") Integer userId);

    void insertCartItem(@Param("cartItem") CartItem cartItem);

    Integer selectCartId(@Param("userId") Integer userId);

    Page<CartItemVo> selectCartItemsById(Page<CartItemVo> page, @Param("cartId") Integer cartId);

    CartItem selectCartItemByCartIdAndSeafoodId(@Param("seafoodId") Integer seafoodId, @Param("cartId") Integer cartId);

}
