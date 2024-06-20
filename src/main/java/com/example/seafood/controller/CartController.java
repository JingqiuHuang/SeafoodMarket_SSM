package com.example.seafood.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.seafood.common.CartItemVo;
import com.example.seafood.common.ParamsVo;
import com.example.seafood.common.ResponseData;
import com.example.seafood.pojo.CartItem;
import com.example.seafood.pojo.Order;
import com.example.seafood.pojo.SeaFood;
import com.example.seafood.pojo.User;
import com.example.seafood.service.CartService;
import com.example.seafood.service.OrderService;
import com.example.seafood.service.SeaFoodService;
import com.example.seafood.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("cart")
@RestController
public class CartController {

    //结算购物车
    @Resource
    OrderService orderService;
    @Autowired
    private CartService cartService;
    @Autowired
    private SeaFoodService seafoodService;
    @Resource
    private UserService userService;

    //根据用户id查询购物车
    @GetMapping("list")
    public ResponseData list(@RequestParam Map<String, String> map) {
        //鉴权
        Integer userId = Integer.parseInt(map.get("userId"));
        String password = map.get("password");
        Integer pageNum = Integer.parseInt(map.get("pageNum"));
        Integer pageSize = Integer.parseInt(map.get("pageSize"));
        User user = userService.getUserById(userId);
        if (user != null && password.equals(user.getPassword())) {
            Page<CartItemVo> list = cartService.getCartItemsByUserId(userId, pageNum, pageSize);
            return ResponseData.ok(list);
        } else
            return ResponseData.fail();
    }

    //删除购物车
    @Transactional
    @PostMapping("remove/{id}")
    public ResponseData remove(@PathVariable String id, @RequestBody ParamsVo params) {
        String password = params.getPassword();
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("userId", String.valueOf(params.getUserId()));
        requestMap.put("password", password);
        if (userService.authPwd(requestMap)) {
            cartService.deleteCartItem(Integer.valueOf(id));
            return ResponseData.ok();
        } else
            return ResponseData.fail();
    }

    //修改购物车数量
    @PostMapping("update/{seafoodId}/{num}")
    public ResponseData update(@PathVariable String seafoodId, @PathVariable String num, @RequestBody ParamsVo params) {
        String password = params.getPassword();
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("userId", String.valueOf(params.getUserId()));
        requestMap.put("password", password);
        if (userService.authPwd(requestMap)) {
            cartService.updateCount(Integer.parseInt(seafoodId), params.getUserId(), Integer.valueOf(num));
            return ResponseData.ok();
        } else
            return ResponseData.fail();
    }

    //清空购物车
    @Transactional
    @PostMapping("clean")
    public ResponseData update(@RequestBody ParamsVo params) {
        String password = params.getPassword();
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("userId", String.valueOf(params.getUserId()));
        requestMap.put("password", password);
        if (userService.authPwd(requestMap)) {
            cartService.cleanCart(params.getUserId());
            return ResponseData.ok();
        } else
            return ResponseData.fail();
    }

    //添加购物车
    @GetMapping("add/{seafoodId}/{userId}")
    @Transactional
    public ResponseData cartAdd(@PathVariable("seafoodId") Integer seafoodId, @PathVariable("userId") Integer userId) {
        try {
            SeaFood seafood = seafoodService.getSeaFoodById(seafoodId);
            cartService.saveCart(userId);
            cartService.saveCartItem(seafood, userId);
        } catch (Exception e) {
            return ResponseData.fail().message(e.getMessage());
        }
        return ResponseData.ok();
    }

    @GetMapping("/buy/{userId}")
    @Transactional
    public ResponseData buy(@PathVariable String userId) {
        try {
            List<CartItem> cartItemList = cartService.listById(userId);
            BigDecimal totalPrice = BigDecimal.valueOf(0);
            for (CartItem c : cartItemList) {
                totalPrice = totalPrice.add(c.getTotalPrice());
            }
            String orderId = UUID.randomUUID().toString();
            Order order = new Order(orderId, LocalDateTime.now(), totalPrice, 0, Integer.parseInt(userId));

            orderService.createOrder(cartItemList, order);
            cartService.cleanCart(Integer.valueOf(userId));
            return ResponseData.ok();
        } catch (Exception e) {
            return ResponseData.fail();
        }
    }
}
