package com.example.seafood.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seafood.common.CartItemVo;
import com.example.seafood.mappers.CartMapper;
import com.example.seafood.pojo.CartItem;
import com.example.seafood.pojo.SeaFood;
import com.example.seafood.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class CartServiceImpl extends ServiceImpl<CartMapper, CartItem> implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void updateCount(Integer seafoodId, Integer userId, Integer count) {
        Integer cartId = cartMapper.selectCartId(userId);

        CartItem cartItem = cartMapper.selectCartItemByCartIdAndSeafoodId(seafoodId, cartId);
        BigDecimal price = cartItem.getPrice();
        BigDecimal totalPrice = price.multiply(new BigDecimal(count));

        cartMapper.updateCountAndPrice(count, totalPrice, seafoodId, cartId);
    }

    @Override
    public void cleanCart(Integer userId) {
        Integer cartId = cartMapper.selectCartId(userId);
        cartMapper.cleanCart(cartId);
    }

    @Override
    public void deleteCartItem(Integer id) {
        cartMapper.deleteCartItem(id);
    }

    @Override
    public void saveCartItem(SeaFood seafood, Integer userId) {
        Integer cartId = cartMapper.selectCartId(userId);

        CartItem item = cartMapper.selectCartItemByCartIdAndSeafoodId(seafood.getId(), cartId);

        if (item == null) {
            CartItem cartItem = new CartItem(null, seafood.getId(), seafood.getName(), 1,
                    seafood.getPrice(), seafood.getPrice(), cartId);

            cartMapper.insertCartItem(cartItem);
        } else {
            Integer count = item.getCount();
            count++;

            BigDecimal price = item.getPrice();
            BigDecimal totalPrice = price.multiply(new BigDecimal(count));
            cartMapper.updateCountAndPrice(count, totalPrice, seafood.getId(), cartId);
        }
    }

    @Override
    public void saveCart(Integer userId) {
        Integer cartId = cartMapper.selectCartId(userId);
        if (cartId == null) {
            cartMapper.insertCart(userId);
        }
    }

    @Override
    public Page<CartItemVo> getCartItemsByUserId(Integer userId, Integer pageNum, Integer pageSize) {
        Page<CartItemVo> page = new Page<>(pageNum, pageSize);
        Page<CartItemVo> cartItemPage = this.baseMapper.selectCartItemsById(page, userId);
        return cartItemPage;
    }

    @Override
    public List<CartItem> listById(String userId) {
        Integer cartId = cartMapper.selectCartId(Integer.valueOf(userId));
        LambdaQueryWrapper<CartItem> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CartItem::getCartId, cartId);
        List<CartItem> cartItemList = baseMapper.selectList(queryWrapper);
        return cartItemList;
    }
}
