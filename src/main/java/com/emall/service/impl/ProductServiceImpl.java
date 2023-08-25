package com.emall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.emall.common.R;
import com.emall.common.params.PageParams;
import com.emall.common.vo.EvaluateVo;
import com.emall.common.vo.ProductAndEvaluate;
import com.emall.common.vo.ProductCountVo;
import com.emall.entity.Evaluate;
import com.emall.entity.Product;
import com.emall.mapper.EvaluateMapper;
import com.emall.mapper.ProductMapper;
import com.emall.mapper.UserMapper;
import com.emall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private EvaluateMapper evaluateMapper;

    @Autowired
    private UserMapper userMapper;

    public List<Product> listProduct(PageParams pageParams) {
        //分页
        Page<Product> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        IPage<Product> productIPage = productMapper.getAllProduct(page);
        return productIPage.getRecords();
    }

    @Override
    public Product getProductById(long id) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getProductId,id);
        queryWrapper.last("limit 1");
        Product product = productMapper.selectOne(queryWrapper);
        return product;
    }

    //查询所有商品，由于前文已使用getAllProduct 此处用getAllProductShow命名
    @Override
    public R<List<Product>> getAllProductShow() {
        List<Product> products = productMapper.selectList(null);
        return R.success(products);
    }

    @Override
    public R<List<Product>> getByProductName(String productName) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getProductName,productName);
        List<Product> products = productMapper.selectList(queryWrapper);
        return R.success(products);
    }

    @Override
    public R<List<Product>> getByProductPrice(String productPrice) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getProductPrice,productPrice);
        List<Product> products = productMapper.selectList(queryWrapper);
        return R.success(products);
    }

    //价格区间查询商品
    @Override
    public R<List<Product>> getByProductPrices(String minPrice,String maxPrice){
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.lt(null != maxPrice, Product::getProductPrice, maxPrice)
                    .ge(null != minPrice, Product::getProductPrice, minPrice);
        List<Product> products = productMapper.selectList(queryWrapper);
        return R.success(products);
    }

    @Override
    public R<String> updateByProductId(Product product) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getProductId,product.getProductId());
        int i = productMapper.update(product,queryWrapper);
        if(i > 0){
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }

    @Override
    public R<String> deleteByProductId(Long productId) {
        LambdaQueryWrapper<Product> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Product::getProductId,productId);
        int i = productMapper.deleteById(productId);
        if(i > 0){
            return R.success("操作成功");
        }
        return R.error("操作失败");
    }

    @Override
    public R<String> insertProduct(Product product) {
        int i = productMapper.insert(product);
        if(i > 0){
            return R.success("操作成功");
        }
        return R.error("操作失败");

    }

    @Override
    public R<String> insertIntoCart(Product product) {
        int i = productMapper.insertIntoCart(product);
        if(i > 0){
            return R.success("操作成功");
        }
        return R.error("操作失败");

    }


    @Override
    public R<List<Product>> orderByPrice(){
        List<Product> products=productMapper.orderByPrice();
        return  R.success(products);
    }

    @Override
    public R<List<Product>> orderBySales(){
        List<Product> products=productMapper.orderBySales();
        return  R.success(products);
    }

    @Override
    public R<List<ProductCountVo>> selectCount(Long productId){
     List<ProductCountVo> products =productMapper.selectCount(productId);
        return R.success(products);
    }

    @Override
    public R<List<ProductCountVo>> guessLike() {
        List<ProductCountVo> products = productMapper.guessYouLike();
        return R.success(products);
    }

    @Override
    public R<List<ProductAndEvaluate>> guessLikeWithEvaluate() {

        List<ProductAndEvaluate> result = new ArrayList<>();
        List<ProductCountVo> products = productMapper.guessYouLike();

        for (ProductCountVo product : products) {
            ProductAndEvaluate productAndEvaluate = new ProductAndEvaluate();
            productAndEvaluate.setProduct(product);
            Long productId = product.getProductId();
            List<Evaluate> evaluates = evaluateMapper.getGoodEvaluateById(productId);
            List<EvaluateVo> evaluateVos  = new ArrayList<>();
            for (Evaluate evaluate : evaluates) {
                EvaluateVo evaluateVo = new EvaluateVo();
                evaluateVo.setEvaluateContent(evaluate.getEvaluateContent());
                evaluateVo.setEvaluateDate(evaluate.getEvaluateDate());
                evaluateVo.setEvaluateLevel(evaluate.getEvaluateLevel());
                LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper();
                evaluateVo.setUserName(userMapper.getNameById(evaluate.getUserId()));
                evaluateVos.add(evaluateVo);
            }
            productAndEvaluate.setEvaluates(evaluateVos);
            result.add(productAndEvaluate);
        }

        return R.success(result);
    }
}
