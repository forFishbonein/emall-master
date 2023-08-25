package com.emall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.emall.common.R;
import com.emall.common.vo.OrderVo;
import com.emall.entity.Order;
import com.emall.mapper.OrderMapper;
import com.emall.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;


    //getByUserId
    @Override
    public R<List<OrderVo>> getByUserId(Long userId) {
//        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Order::getUserId,userId);
        List<OrderVo> orders = orderMapper.getByUserId(userId);
        return R.success(orders);
    }

    @Override
    public R<OrderVo> getByOrderId(Long orderId) {
        OrderVo order = orderMapper.getByOrderId(orderId);
        return R.success(order);
    }

    @Override
    public R<List<OrderVo>> getByProductId(Long productId) {
//        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Order::getProductId,productId);
        List<OrderVo> orders = orderMapper.getByProductId(productId);
        return R.success(orders);
    }

//    @Override
//    public R<List<Order>> getByOrderStatus(String orderStatus) {
//        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Order::getOrderStatus,orderStatus);
//        List<Order> orders = orderMapper.selectList(queryWrapper);
//        return R.success(orders);
//    }

    @Override
    public R<List<OrderVo>> getAllOrder() {
        List<OrderVo> orders = orderMapper.getAllOrder();
        return R.success(orders);
    }

    @Override
    public R<String> updateOrderStatus(Order order) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getOrderId,order.getOrderId());
        int i = orderMapper.update(order,queryWrapper);
        if(i > 0){
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }

    @Override
    public R<String> deleteByOrderId(Long orderId) {
        int i = orderMapper.deleteById(orderId);
        if(i > 0){
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }

    @Override
    public R<String> insertOrder(Order order) {
        int i = orderMapper.insert(order);
        if(i > 0){
            return R.success("操作成功");
        }
        return R.error("操作失败");

    }

    //判断是否购买
    @Override
    public R<String> judgeBuy(Long productId,Long userId) {
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getProductId,productId).eq(Order::getUserId,userId);
        return R.success("已购买");
    }
}
