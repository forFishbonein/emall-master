package com.emall.service;

import com.emall.common.R;
import com.emall.common.params.CartParam;
import com.emall.entity.Cart;

import java.util.List;

public interface CartService {

    R<String> insertCart(Cart cart);

    R<List<Cart>> getAllByUserId(Long userId);

    R<String> deleteByCartId(Long cartId);

    R<String> updateCartCountByCartId(CartParam cart);

    R<String> deleteByUserId(Long userId);
}
