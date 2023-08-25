/*
 * @Time : 2022/9/1 20:16
 * @Author : hao
 * @File : ProductAndEvaluate.java
 * @Software : IntelliJ IDEA
 */
package com.emall.common.vo;

import com.emall.entity.Evaluate;
import com.emall.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductAndEvaluate {

    private ProductCountVo product;
    private List<EvaluateVo> evaluates;

}
