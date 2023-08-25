package com.emall.controller;

import com.emall.common.R;
import com.emall.common.params.CartParam;
import com.emall.common.vo.ProductCartVo;
import com.emall.entity.Cart;
import com.emall.entity.Product;
import com.emall.entity.User;
import com.emall.service.CartService;
import com.emall.service.ProductService;
import com.emall.service.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/carts")
public class CartController {
    /*
    TODO
    当前controller应用场景： 电商端，用户查看购物车
    需求：
    1、 用户查看自己的购物车（列出该用户的所有购物车记录）
    2、 用户新增购物车商品
    3、 用户修改购物车商品数量
    4、 用户删除一条购物车记录
    5、 用户删除多条购物车记录（复选框）
    6、 用户清空购物车
    */

    @Autowired
    private CartService cartService;

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    private ProductService productService;


    @GetMapping("/cart")
    public R<List<ProductCartVo>> selectCart(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        User user = loginService.checkToken(token);
        List<Cart> cartList = cartService.getAllByUserId(user.getUserId()).getData();
        List<ProductCartVo> carts = new ArrayList<>();
        for (Cart cart : cartList) {
            ProductCartVo productCartVo = new ProductCartVo();
            BeanUtils.copyProperties(cart, productCartVo);
//            productCartVo.setCartId(cart.getCartId());
//            productCartVo.setCartCount(cart.getCartCount());
            Long productId = cart.getProductId();
            Product product = productService.getProductById(productId);
            productCartVo.setProductImg(product.getProductImg());
            productCartVo.setProductName(product.getProductName());
            productCartVo.setProductPrice(product.getProductPrice());
            carts.add(productCartVo);
        }
        return R.success(carts);
    }

    @CachePut(value = "cart" , key = "#cart.cartId")
    @PostMapping
    public R<String> insertCart(@RequestBody Cart cart) {
        return cartService.insertCart(cart);
    }

    @CacheEvict(value = "cart" , key = "#orderId")
    @DeleteMapping("/{id}")
    public R<String> deleteCart(@PathVariable("id") Long id) {
        return cartService.deleteByCartId(id);
    }

    @CachePut(value = "cart" , key = "#cart.cartId")
    @PutMapping
    public R<String> updateCart(@RequestBody CartParam cart) {
        return cartService.updateCartCountByCartId(cart);
    }

    @PostMapping("/empty")
    public R<String> emptyCart(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        User user = loginService.checkToken(token);
        return cartService.deleteByUserId(user.getUserId());
    }

}
