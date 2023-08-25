package com.emall.controller;

import com.emall.common.R;
import com.emall.common.vo.OrderVo;
import com.emall.entity.Order;
import com.emall.service.OrderService;
import com.emall.service.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/orders")
public class OrderController {
    /*
    TODO
    当前controller应用场景： 电商端、管理端用户查看订单
    需求：
    1、 电商端，用户查看订单信息 1
    2、 电商端，用户删除订单信息 1
    3、 电商端，用户购买商品后新增订单信息 1
    4、 管理端，根据某商品查看该商品的所有订单信息 1
    5、 管理端，根据某用户查看该用户的所有订单信息 1
    6、 管理端，删除订单信息 1
    7、 管理端，修改订单状态信息 1
    8、 管理端，查看所有订单信息 1
    9、 管理端，根据订单id查询订单信息 1
     */

    /*
    10、 管理端，查询所有订单信息
     */


    @Autowired
    private OrderService orderService;

    //电商端，用户查看订单信息
    //管理端，根据某用户查看该用户的所有订单信息
    // 根据userid得到order信息
    @GetMapping("/user/{userId}")
    public R<List<OrderVo>> getByUserId(@PathVariable("userId") Long userId) {
        R<List<OrderVo>> orders = orderService.getByUserId(userId);
        return orders;
    }

    //管理端，根据某商品id查看该商品的所有订单信息
    // 根据productid得到order信息
    @GetMapping("/product/{productId}")
    public R<List<OrderVo>> getByProductId(@PathVariable("productId") Long productId) {
        R<List<OrderVo>> orders = orderService.getByProductId(productId);
        //TODO 这里需要多表查询，可写mapper
        return orders;
    }

    //管理端，查看所有订单信息
    @GetMapping("/order")
    public R<List<OrderVo>> getAll() {
        R<List<OrderVo>> orders = orderService.getAllOrder();
        return orders;
    }

    //管理端，根据订单id查询订单信息
    @GetMapping("/order/{orderId}")
    public R<OrderVo> getByOid(@PathVariable("orderId") Long orderId) {
        R<OrderVo> order = orderService.getByOrderId(orderId);
        return order;
    }

    //电商端，用户购买商品后新增订单信息
    @PostMapping("/order2")
    //You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'order  ( order_id,
    //user_id,
    //product_id,
    //cart_count,
    //order_date,
    //order_status,
    //de' at line 1
    public R<String> insertOrder(@RequestBody Order order) {
        return orderService.insertOrder(order);
    }

    //管理端，删除订单信息
    //电商端，用户删除订单信息
    @DeleteMapping("/order/{orderId}")
    //You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'order SET deleted='1' WHERE order_id=6  AND deleted='0'' at line 1
    public R<String> deleteOrder(@PathVariable("orderId") Long orderId) {
        return orderService.deleteByOrderId(orderId);
    }

    //管理端，修改订单状态信息
    @PutMapping("/order")
    //You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'order  SET user_id=3,
    //product_id=444,
    //cart_count=20,
    //order_date='2022-09-01 20:3' at line 1
    public R<String> updateOrderStatus(@RequestBody Order order) {
        return orderService.updateOrderStatus(order);
    }

    //判断是否购买
    @PostMapping("/order")
    public R<String> judgeBuy(@RequestBody Long productId,Long userId) {
        return orderService.judgeBuy(productId,userId);
    }


}

