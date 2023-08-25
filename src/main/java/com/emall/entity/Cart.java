package com.emall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

@Data
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    //使用MP的id生成策略
    @TableId(type = IdType.ASSIGN_ID)
    private Long cartId;

    private Long productId;

    private Long userId;

    private Integer cartCount;

    @TableLogic
    private String deleted;
}
