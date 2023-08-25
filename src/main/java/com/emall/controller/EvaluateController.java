package com.emall.controller;

import com.emall.common.R;
import com.emall.common.params.CartParam;
import com.emall.entity.Cart;
import com.emall.entity.Evaluate;
import com.emall.entity.Product;
import com.emall.entity.User;
import com.emall.mapper.OrderMapper;
import com.emall.service.CartService;
import com.emall.service.EvaluateService;
import com.emall.service.ProductService;
import com.emall.service.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/evas")
public class EvaluateController {
        /*
    TODO
    当前controller应用场景： 电商端，用户查看、新增、修改、删除商品评论；管理端，管理员查看、删除商品评论
    需求：
    1、 电商端，用户查看某商品的评论、平均分
    2、 电商端，用户购买商品后可打分、评论（可选）
    3、 电商端，用户修改、删除评论
    4、 管理端，管理员查看某一商品的所有评论
    5、 管理端，管理员查看某用户的所有评论
    6、 管理端，管理员删除某评论
     */


    @Autowired
    private CartService cartService;

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private EvaluateService evaluateService;

    //1、 用户、管理员查看某商品的评论、平均分
    @GetMapping("/product/{productId}")
    public R<List<Evaluate>> getByProduct(@PathVariable("productId")Long productId){
        R<List<Evaluate>> evaluates = evaluateService.getByProductId(productId);
        return evaluates;
    }


//    @GetMapping("/product/{productId}")
//    public R<Double> getAvgLevel(@PathVariable("productId")Long productId){
//        return evaluateService.getAvgLevel(productId);
//    }




    //2、 电商端，用户购买商品后可打分、评论（可选）
    @PostMapping("/evaluate")
    public R<String> insertEvaluate(@RequestBody Evaluate evaluate){
        return evaluateService.insertEvaluate(evaluate);
    }


    //3、 电商端，用户修改、删除评论
    @PutMapping("/evaluate")
    public R<String> updateByEvaluateId (@RequestBody Evaluate evaluate){
        return evaluateService.updateByEvaluateId(evaluate);
    }

    //5、 管理端，管理员查看某用户的所有评论
    @GetMapping("/user/{userId}")
    public R<List<Evaluate>> getByUserId(@PathVariable("userId")Long userId) {
        R<List<Evaluate>> evaluates = evaluateService.getByUserId(userId);
        return evaluates;
    }

    //6、 管理端，管理员删除某评论
    @DeleteMapping("/evaluate/{evaluateId}")
    public R<String> deleteEvaluateById (@PathVariable("evaluateId") Long evaluateId){
        return evaluateService.deleteByEvaluateId(evaluateId);
    }

}
