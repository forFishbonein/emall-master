package com.emall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.emall.common.vo.OrderVo;
import com.emall.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    //根据order_id查询order信息（条件判断order、product二者deleted字段为0）
    OrderVo getByOrderId (@Param("orderId") Long orderId);

    //根据user_id查询order信息（条件判断order、product二者deleted字段为0）
    List<OrderVo> getByUserId (@Param("userId") Long userId);

    //根据product_id查询order信息（条件判断order、product二者deleted字段为0）
    List<OrderVo> getByProductId (@Param("productId") Long productId);

    //根据order_status获取order信息（条件判断order、product二者deleted字段为0）
//    List<Order> getByOrderStatus (@Param("orderStatus") String orderStatus);

    //查询所有order信息
    List<OrderVo> getAllOrder();

//    //新增order信息
//    int insertOrder (@Param("Order") Order order);
//
//    //删除order信息
//    int deleteByOrderId (@Param("OrderId") Long orderId);
//
//    //根据order_id修改order_status
//    int updateOrderStatus (@Param("Order") Order order);

}
