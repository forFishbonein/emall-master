package com.emall.common.params;

import com.emall.entity.Cart;
import lombok.Data;

@Data
public class CartParam extends Cart {

    private Long cartId;

    private Integer cartCount;
}
