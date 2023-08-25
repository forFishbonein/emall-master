package com.emall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.emall.entity.Evaluate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EvaluateMapper extends BaseMapper<Evaluate> {

//    //根据evaluate_id查询evaluate信息（条件判断user、product、evaluate三者deleted字段为0）
//    Evaluate getByEvaluateId (@Param("evaluateId") Long evaluateId);
//
//    //根据user_id查询evaluate信息（条件判断user、product、evaluate三者deleted字段为0）
//    Evaluate getByUserId (@Param("userId") Long userId);
//
//    //根据product_id查询evaluate信息（条件判断user、product、evaluate三者deleted字段为0）
//    Evaluate getByProductId (@Param("productId") Long productId);
//
//    int insertEvaluate (@Param("Evaluate") Evaluate evaluate);
//
//    int updateByEvaluatetId (@Param("evaluate") Evaluate evaluate);
//
//    int deleteByEvaluateId (@Param("EvaluateId") Long evaluateId);
//
//    List<Evaluate> getAllByUserId (@Param("userId") Long userId);
//
    List<Evaluate> getGoodEvaluateById (@Param("productId") Long productId);

    double getAvgLevel (@Param("productId") Long productId);
//
//    int deleteByUserId (@Param("userId") Long userId);

}
