package com.emall.common.vo;

import com.emall.entity.Order;
import lombok.Data;

@Data
public class OrderVo extends Order {

    private String productName;

    private String productPrice;

    private String productImg;

}
