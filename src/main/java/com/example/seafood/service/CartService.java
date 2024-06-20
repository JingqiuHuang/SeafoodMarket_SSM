package com.example.seafood.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seafood.common.CartItemVo;
import com.example.seafood.pojo.CartItem;
import com.example.seafood.pojo.SeaFood;

import java.util.List;

public interface CartService extends IService<CartItem> {

    void updateCount(Integer seafoodId, Integer userId, Integer count);

    void cleanCart(Integer userId);

    void deleteCartItem(Integer id);

    void saveCartItem(SeaFood seafood, Integer cartId);

    void saveCart(Integer userId);

    Page<CartItemVo> getCartItemsByUserId(Integer userId, Integer pageNum, Integer pageSize);

    List<CartItem> listById(String userId);
}
