package com.example.seafood.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.seafood.mappers.OrderMapper;
import com.example.seafood.mappers.SeaFoodMapper;
import com.example.seafood.pojo.CartItem;
import com.example.seafood.pojo.Order;
import com.example.seafood.pojo.OrderItem;
import com.example.seafood.pojo.SeaFood;
import com.example.seafood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private SeaFoodMapper seafoodMapper;

    @Override
    public void updateStatus(String orderId, Integer status) {
        orderMapper.updateStatus(orderId, status);
    }

    @Override
    public Page<Order> getOrderList(String useId, Integer pageNum, Integer pageSize, String queryKey, String queryKind) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        if (useId != null)
            queryWrapper.eq(Order::getUserId, useId);
        if (queryKind == null) {
            if (queryKey == null)
                queryKey = "";
            queryWrapper.like(Order::getOrderId, queryKey);
        } else {
            if (queryKind.equals("order_id"))
                queryWrapper.like(Order::getOrderId, queryKey);
            if (queryKind.equals("create_time"))
                queryWrapper.like(Order::getCreateTime, queryKey);
            if (queryKind.equals("status")) {
                if (queryKey.contains("已发货"))
                    queryKey = "1";
                else if (queryKey.contains("已下单"))
                    queryKey = "0";
                queryWrapper.like(Order::getStatus, queryKey);
            }
        }
        Page<Order> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<Order> getOrder() {
        return orderMapper.selectOrder();
    }

//    @Override
//    public List<Order> getOrderByUserId(Integer userId) {
//        return orderMapper.selectOrderByUserId(userId);
//    }

    @Override
    @Transactional
    public void createOrder(List<CartItem> list, Order order) {
        orderMapper.insertOrder(order);
        for (CartItem cartItem : list) {
            Integer seafoodId = cartItem.getSeafoodId();
            SeaFood seaFood = seafoodMapper.selectSeafoodById(seafoodId);
            String provider = seaFood.getProvider();
            String kind = seaFood.getKind();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(),
                    cartItem.getPrice(), cartItem.getTotalPrice(), order.getOrderId(), provider, kind, seafoodId);

            orderMapper.insertOrderItem(orderItem);

            SeaFood seafood = seafoodMapper.selectSeafoodById(cartItem.getSeafoodId());
            seafood.setSales(seafood.getSales() + cartItem.getCount());
            seafood.setStock(seafood.getStock() - cartItem.getCount());

            seafoodMapper.updateSeafood(seafood);
        }
    }


    @Override
    public List<OrderItem> getOrderItemByUserId(String orderId) {
        List<OrderItem> orderItems = baseMapper.selectOrderItemByOrderId(orderId);
        return orderItems;
    }

    @Override
    public void deleteById(String orderId) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getOrderId, orderId);
        this.remove(queryWrapper);

    }
}
