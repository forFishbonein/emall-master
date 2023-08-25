package com.emall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.emall.common.R;
import com.emall.common.params.CartParam;
import com.emall.entity.Cart;
import com.emall.mapper.CartMapper;
import com.emall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public R<List<Cart>> getAllByUserId(Long userId) {
        LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getUserId, userId);
        List<Cart> carts = cartMapper.selectList(queryWrapper);
        return R.success(carts);
    }

    @Override
    public R<String> insertCart(Cart cart) {
        int i = cartMapper.insert(cart);
        if (i > 0) {
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }


    @Override
    public R<String> deleteByCartId(Long cartId) {
        LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getCartId,cartId);
        int i = cartMapper.deleteById(queryWrapper);
        if (i > 0){
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }

    @Override
    public R<String> updateCartCountByCartId(CartParam cart) {
        LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getCartId,cart.getCartId());
        int i = cartMapper.update(cart,queryWrapper);
        if (i > 0){
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }


    @Override
    public R<String> deleteByUserId(Long userId){
        LambdaQueryWrapper<Cart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Cart::getUserId, userId);
        int delete = cartMapper.delete(queryWrapper);
        if (delete > 0){
            return R.success("清空成功");
        }
        return R.error("清空失败");
    }

}
