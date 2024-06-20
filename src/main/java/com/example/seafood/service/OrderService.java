package com.example.seafood.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.seafood.pojo.CartItem;
import com.example.seafood.pojo.Order;
import com.example.seafood.pojo.OrderItem;

import java.util.List;

public interface OrderService extends IService<Order> {

    void updateStatus(String orderId, Integer status);

    Page<Order> getOrderList(String userId, Integer pageNum, Integer pageSize, String queryKey, String queryKind);

    List<Order> getOrder();


    void createOrder(List<CartItem> list, Order order);

    List<OrderItem> getOrderItemByUserId(String orderId);

    void deleteById(String orderId);
}
