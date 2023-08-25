/*
 * @Time : 2022/9/1 21:36
 * @Author : hao
 * @File : ProductCountVo.java
 * @Software : IntelliJ IDEA
 */
package com.emall.common.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

@Data
public class ProductCountVo {

    private Long productId;

    private String productName;

    private String productPrice;

    private String productImg;

    private String productDetails;

    private String deleted;

    private Long count;
}
