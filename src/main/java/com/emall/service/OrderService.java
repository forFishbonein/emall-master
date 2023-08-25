package com.emall.service;

import com.emall.common.R;
import com.emall.common.vo.OrderVo;
import com.emall.entity.Cart;
import com.emall.entity.Order;

import java.util.List;


public interface OrderService {

    R<List<OrderVo>> getByUserId(Long userId);
    R<OrderVo> getByOrderId(Long orderId);
    R<List<OrderVo>> getByProductId(Long productId);
    R<List<OrderVo>> getAllOrder();

    R<String> updateOrderStatus(Order order);
    R<String> deleteByOrderId(Long orderId);
    R<String> insertOrder(Order order);


    //判断是否购买
    R<String> judgeBuy(Long productId, Long userId);
}
