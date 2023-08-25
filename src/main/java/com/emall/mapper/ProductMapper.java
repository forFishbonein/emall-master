package com.emall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.emall.common.vo.ProductCountVo;
import com.emall.entity.Evaluate;
import com.emall.entity.Order;
import com.emall.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

//    //根据product_id查询product信息
//    Product getByProductId (@Param("productId") Long productId);
//
//
//    //根据product_name查询product信息
//    Product getByProductName (@Param("productName") Long productName);
//
//    //新增product信息
//    int insertProduct (@Param("Product") Product Product);
//
//    //根据product_id删除product信息
//    int deleteByProductId (@Param("ProductId") Long ProductId);
//
//    //根据product_id修改product信息
//    int updateByProductId (@Param("product") Product product);
//
    //查询所有product信息
    IPage<Product> getAllProduct(IPage<Product> page);

    //猜你喜欢
    List<ProductCountVo> guessYouLike();



    //根据时间查询销量
    List<ProductCountVo> selectCount(Long productId);

    //加入购物车
    int insertIntoCart(@Param("product") Product product);

    List orderByPrice();
    List orderBySales();

}
