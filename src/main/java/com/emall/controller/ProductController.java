package com.emall.controller;

import com.emall.common.R;
import com.emall.common.params.PageParams;
import com.emall.common.vo.ProductAndEvaluate;
import com.emall.common.vo.ProductCountVo;
import com.emall.entity.Product;
import com.emall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    /*
    TODO
    当前controller应用场景： 电商端，用户购买商品；管理端，查看、新增、修改、删除商品
    需求：
    1、 电商端，用户将商品添加至购物车
    2、 管理端，查看商品销量（根据不同时间）
    3、 管理端，新增商品  1
    4、 管理端，修改商品信息  1
    5、 管理端，删除商品    1
    6、 电商端，商品展示（首页随机展示、猜你喜欢等板块）
     */

   @Autowired
   private ProductService productService;

     //2、管理端：查看商品销量（根据不同时间）  这个需求没有全部做完
    @GetMapping("/{productid}")
    public R<List<ProductCountVo>> selectCount(@PathVariable("productid") Long productId){
       return productService.selectCount(productId);
    }


    //新增商品
    @PostMapping("/product")
    public R<String> insertProduct(@RequestBody Product product){
         return productService.insertProduct(product);
     }

    //修改商品信息
    @PutMapping
    public R<String> updateByProductId(@RequestBody Product product) {
        return productService.updateByProductId(product);
    }

    //删除商品
    @DeleteMapping("/{productId}")
    public R<String> deleteByProductId(@PathVariable("productId") Long productId){
         return productService.deleteByProductId(productId);
    }
     //有问题
    @PostMapping ("/all")
    public R<List<Product>> listProduct(@RequestBody PageParams pageParams) {
        return R.success(productService.listProduct(pageParams));

    }

    @GetMapping("/like")
    public R<List<ProductCountVo>> guessLike(){
        return productService.guessLike();
    }

    @GetMapping("/likeAndEva")
    public R<List<ProductAndEvaluate>> guessLikeWithEva(){
        return productService.guessLikeWithEvaluate();
    }

    //根据商品id查找商品信息
    @PostMapping("/detail/{productId}")
    public R<Product> getProductById(@PathVariable("productId") Long productId){
       return R.success(productService.getProductById(productId));
    }
    @GetMapping("/orderByPrice")
    public R<List<Product>> orderByPrice(){return productService.orderByPrice();}

    @GetMapping("/orderBySales")
    public R<List<Product>> orderBySales(){return productService.orderBySales();}

}
