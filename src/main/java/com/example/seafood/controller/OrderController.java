package com.example.seafood.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.seafood.common.ResponseData;
import com.example.seafood.pojo.Order;
import com.example.seafood.pojo.OrderItem;
import com.example.seafood.service.OrderService;
import com.example.seafood.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("order")
@RestController
public class OrderController {

    @Resource
    UserService userService;
    @Resource
    private OrderService orderService;

    //查询当前用户所有订单
    @GetMapping("list")
    public ResponseData list(@RequestParam Map<String, String> map) {
        Integer userId = Integer.parseInt(map.get("userId"));
        Integer pageNum = Integer.parseInt(map.get("pageNum"));
        Integer pageSize = Integer.parseInt(map.get("pageSize"));
        String queryKind = map.get("queryKind");
        String queryKey = map.get("queryKey");
        // 鉴权
        if (userService.authPwd(map)) {
            Page<Order> list = orderService.getOrderList(String.valueOf(userId), pageNum, pageSize, queryKey, queryKind);
            return ResponseData.ok(list);
        } else
            return ResponseData.fail();
    }

    //查询所有用户订单
    @GetMapping("listAll")
    public ResponseData listById(@RequestParam Map<String, String> map) {
        Integer pageNum = Integer.parseInt(map.get("pageNum"));
        Integer pageSize = Integer.parseInt(map.get("pageSize"));
        String queryKind = map.get("queryKind");
        String queryKey = map.get("queryKey");
        // 鉴权
        if (userService.authAdmin(map)) {
            Page<Order> list = orderService.getOrderList(null, pageNum, pageSize, queryKey, queryKind);
            return ResponseData.ok(list);
        } else
            return ResponseData.fail();
    }

    //查询订单详细信息
    @GetMapping("details/{orderId}")
    public ResponseData details(@RequestParam Map<String, String> map, @PathVariable String orderId) {
        if (userService.authPwd(map)) {
            List<OrderItem> list = orderService.getOrderItemByUserId(orderId);
            return ResponseData.ok(list);
        } else
            return ResponseData.fail();
    }

    //订单发货
    @GetMapping("push/{orderId}")
    public ResponseData push(@PathVariable String orderId, @RequestParam Map<String, String> map) {
        if (userService.authPwd(map)) {
            orderService.updateStatus(orderId, 1);
            return ResponseData.ok();
        } else
            return ResponseData.fail();
    }

    //订单删除
    @GetMapping("remove/{orderId}")
    public ResponseData delete(@PathVariable String orderId, @RequestParam Map<String, String> map) {
        if (userService.authPwd(map)) {
            orderService.deleteById(orderId);
            return ResponseData.ok();
        } else
            return ResponseData.fail();
    }
}
