package com.emall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.emall.common.params.CartParam;
import com.emall.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
//
//    //根据cart_id查询cart信息
//    Cart getByCartId (@Param("cartId") Long cartId);
//
//    //根据product_id查询cart信息
//    Cart getByProductId (@Param("productId") Long productId);
//
//    //根据user_id查询cart信息
//    Cart getByUserId (@Param("userId") Long userId);
//
//根据cart_id修改商品数量
int updateCartCountByCartId(@Param("cart") CartParam cart);
//
//    //根据cart_id删除cart信息（更新deleted字段为1）
//    int deleteByCartId(@Param("cartId") Long cartId);
//
//    //根据user_id清空cart信息（更新deleted字段为1）


}
