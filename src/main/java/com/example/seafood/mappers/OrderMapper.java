package com.example.seafood.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.seafood.pojo.Order;
import com.example.seafood.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {

    void insertOrderItem(@Param("orderItem") OrderItem orderItem);

    void insertOrder(@Param("order") Order order);

    void updateStatus(@Param("orderId") String orderId, @Param("status") Integer status);

    List<OrderItem> selectOrderItemByOrderId(@Param("orderId") String orderId);

    List<Order> selectOrderByUserId(@Param("userId") Integer userId);

    List<Order> selectOrder();

    void removeOrderItem(@Param("orderId") String orderId);

}
