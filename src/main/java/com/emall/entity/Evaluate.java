package com.emall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

@Data
public class Evaluate implements Serializable {
    private static final long serialVersionUID = 1L;

    //使用MP的id生成策略
    @TableId(type = IdType.ASSIGN_ID)
    private Long evaluateId;

    private Long userId;

    private Long productId;

    private String evaluateLevel;

    private String evaluateContent;

    private String evaluateDate;

    @TableLogic
    private String deleted;
}
