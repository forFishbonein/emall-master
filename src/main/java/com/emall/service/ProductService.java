package com.emall.service;

import com.emall.common.R;
import com.emall.common.params.PageParams;
import com.emall.common.vo.ProductAndEvaluate;
import com.emall.common.vo.ProductCountVo;
import com.emall.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {

    List<Product> listProduct(PageParams pageParams);

    Product getProductById(long id);

    R<List<Product>> getAllProductShow();

    R<List<Product>> getByProductName(String productName);

    R<List<Product>> getByProductPrice(String productPrice);

    R<List<Product>> getByProductPrices(String productPrice1,String productPrice2);

    R<String> updateByProductId(Product product);

    R<String> deleteByProductId(Long productId);

    R<String> insertProduct(Product product);

    R<String> insertIntoCart(Product product);

    R<List<ProductCountVo>> guessLike();

    R<List<ProductAndEvaluate>> guessLikeWithEvaluate();

    R<List<Product>> orderBySales();

    R<List<ProductCountVo>> selectCount(Long productId);

    R<List<Product>> orderByPrice();
}
