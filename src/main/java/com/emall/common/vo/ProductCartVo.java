/*
 * @Time : 2022/9/4 15:00
 * @Author : hao
 * @File : ProductCartVo.java
 * @Software : IntelliJ IDEA
 */
package com.emall.common.vo;

import lombok.Data;

@Data
public class ProductCartVo {

    private Long cartId;

    private Integer cartCount;

    private String productName;

    private Double productPrice;

    private String productImg;

}
